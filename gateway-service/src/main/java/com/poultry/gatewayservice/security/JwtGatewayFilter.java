package com.poultry.gatewayservice.security;

import com.poultry.gatewayservice.client.TokenGrpcClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class JwtGatewayFilter implements GlobalFilter {

    private final TokenGrpcClient tokenGrpcClient;

    public JwtGatewayFilter(TokenGrpcClient tokenGrpcClient) {
        this.tokenGrpcClient = tokenGrpcClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        // Allow public endpoints
        if (path.startsWith("/api/v1/auth/**")) {
            return chain.filter(exchange);
        }

        // Validate Authorization header
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        return Mono.fromCallable(() -> tokenGrpcClient.validateToken(token))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(claims -> {
                    String id = claims.getOrDefault("id", "");
                    String roles = claims.getOrDefault("roles", "");

                    ServerHttpRequest mutatedRequest = request.mutate()
                            .header("X-Id", id)
                            .header("X-Roles", roles)
                            .build();

                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                })
                .onErrorResume(ex -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }
}

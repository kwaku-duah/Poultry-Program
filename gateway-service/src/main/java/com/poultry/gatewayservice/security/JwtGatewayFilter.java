package com.poultry.gatewayservice.security;

import com.poultry.gatewayservice.client.TokenGrpcClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

        if (request.getPath().toString().startsWith("/api/v1/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return  exchange.getResponse().setComplete();
        }

        return Mono.fromCallable(() -> tokenGrpcClient.validateToken(authHeader.substring(7)))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(claims ->  {
                    ServerHttpRequest mutatedRequest = request.mutate()
                            .header("X-Id", claims.get("id"))
                            .header("X-Roles", claims.get("roles"))
                            .build();

                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                })
                .onErrorResume(e -> {
                    exchange.getResponse().setRawStatusCode(HttpStatus.UNAUTHORIZED.value());
                    return exchange.getResponse().setComplete();
                });
    }
}

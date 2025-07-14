package com.poultry.gatewayservice.security;

import com.poultry.gatewayservice.client.TokenGrpcClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class JwtGatewayFilter implements GlobalFilter, Ordered {

    private static final String X_ID = "X-Id";
    private static final String X_ROLES = "X-Roles";

    private final TokenGrpcClient tokenGrpcClient;

    public JwtGatewayFilter(TokenGrpcClient tokenGrpcClient) {
        this.tokenGrpcClient = tokenGrpcClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("[JwtGatewayFilter] >> Executing filter");

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        System.out.println("[JwtGatewayFilter] >> Request path: " + path);

        if (path.startsWith("/api/v1/auth") || path.startsWith("/api/v1/users")) {
            System.out.println("[JwtGatewayFilter] >> Public endpoint, skipping auth");
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst("Authorization");
        System.out.println("[JwtGatewayFilter] >> Authorization header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("[JwtGatewayFilter] >> Missing or invalid Authorization header");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        System.out.println("[JwtGatewayFilter] >> Extracted token: " + token);

        return Mono.fromCallable(() -> {
                    System.out.println("[JwtGatewayFilter] >> Validating token...");
                    return tokenGrpcClient.validateToken(token);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(claims -> {
                    String id = claims.getOrDefault("id", "");
                    String roles = claims.getOrDefault("roles", "");
                    System.out.println("[JwtGatewayFilter] >> Token valid. ID: " + id + " | Roles: " + roles);

                    ServerHttpRequest mutatedRequest = request.mutate()
                            .header(X_ID, id)
                            .header(X_ROLES, roles)
                            .header("Authorization", "Bearer " + token)
                            .build();

                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                })
                .onErrorResume(ex -> {
                    System.out.println("[JwtGatewayFilter] >> Token validation failed: " + ex.getMessage());
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }

    @Override
    public int getOrder() {
        return -1; // Ensure this runs early
    }
}

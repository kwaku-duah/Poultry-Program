package com.poultry.gatewayservice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/api/v1/auth/**", "/v1/test/see/**")
                        .uri("lb://AUTH-SERVICE"))
                .route("user-service", r -> r
                        .path("/api/v1/users/**", "/api/v1/upgrade/**")
                        .uri("lb://USER-SERVICE"))
                .route("farmer-service", r -> r
                        .path("/api/v1/farmers/**", "/api/v1/coops/**", "/api/v1/eggs/**",
                        "/api/v1/financials/**", "/api/v1/mortality/**", "/api/v1/schedules/**", "/api/v1/vaccines/**")
                        .uri("lb://FARMER-SERVICE"))
                .build();
    }
}

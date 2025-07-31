package com.poultry.messageservice.config;



import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {

        List<String> userIdHeader = request.getHeaders().get("X-Id");
        List<String> rolesHeader = request.getHeaders().get("X-Roles");

        if (userIdHeader != null && !userIdHeader.isEmpty()) {
            attributes.put("userId", userIdHeader.get(0));
        }

        if (rolesHeader != null && !rolesHeader.isEmpty()) {
            attributes.put("roles", rolesHeader.get(0)); // comma-separated string
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
        // no-op
    }
}


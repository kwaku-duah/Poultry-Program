package com.poultry.gatewayservice.controller;

import com.poultry.gatewayservice.client.TokenGrpcClient;
import com.poultry.token.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class TokenController {
    private final TokenGrpcClient tokenGrpcClient;

    public TokenController(TokenGrpcClient tokenGrpcClient) {
        this.tokenGrpcClient = tokenGrpcClient;
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> getToken(@RequestParam String token) {
        Map<String, String> response = tokenGrpcClient.validateToken(token);
        return ResponseEntity.ok(response);

    }


}

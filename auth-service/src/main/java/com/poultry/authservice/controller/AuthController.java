package com.poultry.authservice.controller;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.client.UserGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final UserGrpcClient userGrpcClient;

    @GetMapping("/auth")
    public UserResponse getUser(@RequestParam String email) {
        return userGrpcClient.getUserByEmail(email);
    }
}

package com.poultry.authservice.controller;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.client.UserGrpcClient;
import com.poultry.authservice.dto.LoginResponseDto;
import com.poultry.authservice.dto.UserDto;
import com.poultry.authservice.service.JwtService;
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
    private final JwtService jwtService;

    @GetMapping("/auth")
    public LoginResponseDto getUser(@RequestParam String email) {
        UserResponse user = userGrpcClient.getUserByEmail(email);

        if(user == null) {
            throw new RuntimeException("User not found");
        }


    }
}

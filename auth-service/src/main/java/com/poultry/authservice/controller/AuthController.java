package com.poultry.authservice.controller;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.dto.LoginResponseDto;

import com.poultry.authservice.service.AuthService;
import com.poultry.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/auth")
    public LoginResponseDto getUser(@RequestBody LoginRequest loginRequest) {
       UserResponse user = authService.fullUser(loginRequest);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return  new LoginResponseDto (
                user.getId(), user.getFullName(),user.getEmail(),new HashSet<>(user.getRolesList()),accessToken, refreshToken
        );

    }
}

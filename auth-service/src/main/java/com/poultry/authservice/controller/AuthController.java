package com.poultry.authservice.controller;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.client.UserGrpcClient;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.dto.LoginResponseDto;

import com.poultry.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final UserGrpcClient userGrpcClient;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth")
    public LoginResponseDto getUser(@RequestBody LoginRequest loginRequest) {
        UserResponse user = userGrpcClient.getUserByEmail(loginRequest.email());

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        if(!bCryptPasswordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        return  new LoginResponseDto (
                user.getId(), user.getEmail(),user.getPassword(), new HashSet<>(user.getRolesList()),accessToken, refreshToken
        );


    }
}

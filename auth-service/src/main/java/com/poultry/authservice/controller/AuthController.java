package com.poultry.authservice.controller;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.dto.LoginResponseDto;

import com.poultry.authservice.service.AuthService;
import com.poultry.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> getUser(@RequestBody LoginRequest loginRequest) {
       UserResponse user = authService.fullUser(loginRequest);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);



        return  ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto (
                user.getId(), user.getFullName(),user.getEmail(),new HashSet<>(user.getRolesList()),accessToken, refreshToken
        ));

    }

    @GetMapping("/oauth2/login")
    public ResponseEntity<LoginResponseDto> oAuth2Login(@AuthenticationPrincipal OAuth2User oAuth2User) {
        UserResponse user = authService.oAuthSignin(oAuth2User);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(null, null, null, null, null, null));
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(
                user.getId(), user.getFullName(), user.getEmail(), new HashSet<>(user.getRolesList()), accessToken, refreshToken
        ));
    }
}

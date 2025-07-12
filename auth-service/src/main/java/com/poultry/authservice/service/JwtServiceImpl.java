package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${secret.key}")
    private SecretKey secretKey;

    private final Duration accessTokenExpiration = Duration.ofMinutes(45);

    private final Duration refreshTokenExpiration = Duration.ofDays(7);

    @Override
    public String generateAccessToken(UserResponse user) {
        return "";
    }

    @Override
    public String generateRefreshToken(UserResponse user) {
        return "";
    }
}

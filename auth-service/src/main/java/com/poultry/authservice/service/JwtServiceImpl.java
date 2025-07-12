package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${secret.key}")
    private String secretKeyString;

    private final Duration accessTokenExpiration = Duration.ofMinutes(45);
    private final Duration refreshTokenExpiration = Duration.ofDays(7);

    public SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateAccessToken(UserResponse user) {
        Instant now = Instant.now();
        Instant accessExpiry = now.plus(accessTokenExpiration);


        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("roles", user.getRolesList().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .issuedAt(Date.from(now))
                .expiration(Date.from(accessExpiry))
                .signWith(getSignKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(UserResponse user) {
        Instant now = Instant.now();
        Instant refreshExpiry = now.plus(refreshTokenExpiration);
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(Date.from(now))
                .expiration(Date.from(refreshExpiry))
                .signWith(getSignKey())
                .compact();
    }
}


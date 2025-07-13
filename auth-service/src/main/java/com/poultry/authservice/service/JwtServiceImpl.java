package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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


        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("roles", user.getRolesList().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(accessTokenExpiration)))
                .signWith(getSignKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(UserResponse user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(refreshTokenExpiration)))
                .signWith(getSignKey())
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            Claims claim = getClaimsFromToken(token);
            return claim.getExpiration().after(new Date());
        } catch (JwtException ex) {
            return false;
        }
    }

    @Override
    public String extractUserId(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    @Override
    public String extractRole(String token) {
        return getClaimsFromToken(token).get("roles", String.class);
    }


}


package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;

public interface JwtService {
    String generateAccessToken(UserResponse user);
    String generateRefreshToken(UserResponse user);
    boolean isValidToken(String token);
    String extractUserId(String token);
    String extractRole(String token);

}

package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.dto.LoginResponseDto;

public interface JwtService {
    String generateAccessToken(UserResponse user);
    String generateRefreshToken(UserResponse user);
    LoginResponseDto fullUser(LoginRequest loginRequest);
}

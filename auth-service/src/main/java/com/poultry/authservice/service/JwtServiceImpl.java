package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${secret.key}")
    private SecretKey secretKey;



    @Override
    public String generateAccessToken(UserResponse user) {
        return "";
    }

    @Override
    public String generateRefreshToken(UserResponse user) {
        return "";
    }
}

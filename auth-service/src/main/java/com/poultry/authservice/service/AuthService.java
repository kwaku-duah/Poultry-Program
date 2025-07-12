package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface AuthService {
    UserResponse fullUser(LoginRequest loginRequest);
}

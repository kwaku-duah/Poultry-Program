package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.dto.LoginRequest;



public interface AuthService {
    UserResponse fullUser(LoginRequest loginRequest);
}

package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.dto.LoginRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;


public interface AuthService {
    UserResponse fullUser(LoginRequest loginRequest);
    UserResponse oAuthSignin(OAuth2User oAuth2User);
}

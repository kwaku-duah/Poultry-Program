package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.client.UserGrpcClient;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.dto.LoginResponseDto;
import com.poultry.authservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserGrpcClient userGrpcClient;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponse fullUser(LoginRequest loginRequest) {
        UserResponse user = userGrpcClient.getUserByEmail(loginRequest.email());

        if (user == null) {
            throw new UserNotFoundException("user " + loginRequest.email() + " not found");
        }

        if(!bCryptPasswordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("password does not match");
        }
        return user;
    }
}

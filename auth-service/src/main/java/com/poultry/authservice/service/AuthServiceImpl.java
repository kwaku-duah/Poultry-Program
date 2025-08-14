package com.poultry.authservice.service;

import com.poultry.authservice.UserResponse;
import com.poultry.authservice.client.UserGrpcClient;
import com.poultry.authservice.dto.LoginRequest;
import com.poultry.authservice.exception.NotDefaultRegistrantException;
import com.poultry.authservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

        if (user.getIsOauthUser()) {
            throw new NotDefaultRegistrantException("User" + loginRequest.email() + " is not default registrant");
        }

        if(!bCryptPasswordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("password does not match");
        }
        return user;
    }

    @Override
    public UserResponse oAuthSignin(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        UserResponse user = userGrpcClient.getUserByEmail(email);
        if (user == null || !user.getIsOauthUser()) {
            throw new NotDefaultRegistrantException(email + "not found or registered with oauth2");
        }
        return user;
    }
}

package com.poultry.authservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poultry.authservice.UserResponse;
import com.poultry.authservice.service.AuthService;
import com.poultry.authservice.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService authService;
    private final JwtService jwtService;
    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (!(authentication.getPrincipal() instanceof OAuth2User oAuth2User)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid OAuth2 user");
            return;
        }

        // Get user from OAuth2
        UserResponse user = authService.oAuthSignin(oAuth2User);
        if (user == null || !user.getIsOauthUser()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found or not an OAuth2 user");
            return;
        }


        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        String roles = user.getRolesList()
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));


        String redirectUrl = String.format(
                "http://localhost:5173/oauth2/redirect?accessToken=%s&refreshToken=%s&role=%s&fullName=%s&id=%s&email=%s",
                accessToken,
                refreshToken,
                roles,
                java.net.URLEncoder.encode(user.getFullName(), java.nio.charset.StandardCharsets.UTF_8),
                user.getId(),
                java.net.URLEncoder.encode(user.getEmail(), java.nio.charset.StandardCharsets.UTF_8)
        );
        response.sendRedirect(redirectUrl);
    }
}

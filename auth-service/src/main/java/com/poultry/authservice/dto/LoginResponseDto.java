package com.poultry.authservice.dto;

import com.poultry.authservice.Role;

import java.util.Set;

public record LoginResponseDto(Long id, String fullName, String email, Set<Role> roles, String accessToken, String refreshToken) {
}

package com.poultry.authservice.dto;


import java.util.Set;

public record UserDto(Long id, String email, String password, Set<com.poultry.authservice.Role> roles) {
}

package com.poultry.userservice.dto;

import com.poultry.userservice.entity.Level;

public record FarmRoleRequestDto(Level experience,
                                 String description) {
}

package com.poultry.farmerservice.dto;

import jakarta.validation.constraints.NotBlank;

public record FarmRequestDto(
        @NotBlank String farmName,
        @NotBlank String phoneNumber,
        @NotBlank String location,
        @NotBlank String gpsAddress

) {
}

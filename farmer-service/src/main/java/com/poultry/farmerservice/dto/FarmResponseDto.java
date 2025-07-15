package com.poultry.farmerservice.dto;

public record FarmResponseDto(
        String farmerId,
        String farmName,
        String phoneNumber,
        String location,
        String gpsAddress

) {
}

package com.poultry.farmerservice.dto;

import java.util.List;

public record FarmResponseDto(
        String farmerId,
        String farmName,
        String phoneNumber,
        String location,
        String gpsAddress,
        List<CoopResponseDto> coops

) {
}

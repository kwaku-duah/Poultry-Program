package com.poultry.farmerservice.dto;

import com.poultry.farmerservice.entity.BreedType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CoopResponseDto(
        String breedName,
        BreedType breedType,
        LocalDate dateHatched,
        Integer numberOfBirds,
        BigDecimal unitPrice
) {
}

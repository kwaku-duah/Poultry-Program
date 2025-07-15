package com.poultry.farmerservice.dto;

import com.poultry.farmerservice.entity.BreedType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CoopRequestDto(
        @NotBlank String breedName,
        @NotNull BreedType breedType,
        @NotNull @PastOrPresent LocalDate dateHatched,
        @NotNull @Min(1) Integer numberOfBirds,
        @NotNull@DecimalMin(value = "0.01") BigDecimal unitPrice
) {
}

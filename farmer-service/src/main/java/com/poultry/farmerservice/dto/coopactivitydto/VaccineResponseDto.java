package com.poultry.farmerservice.dto.coopactivitydto;

import java.time.LocalDateTime;

public record VaccineResponseDto(
        Long id,
        LocalDateTime date,
        Long coopId,
        String vaccineName,
        String vaccineType,
        String dosage,
        String durationDosage,
        String reason,
        String description
) {
}

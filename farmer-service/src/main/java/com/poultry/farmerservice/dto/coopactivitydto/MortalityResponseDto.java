package com.poultry.farmerservice.dto.coopactivitydto;

import java.time.LocalDateTime;

public record MortalityResponseDto(
        LocalDateTime date,
        Long coopId,
        Integer numberOfDeaths,
        String reason,
        String report
) {
}

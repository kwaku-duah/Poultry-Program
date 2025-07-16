package com.poultry.farmerservice.dto.coopactivitydto;

import java.time.LocalDateTime;

public record MortalityRequestDto(
        LocalDateTime datetime,
        Long coopId,
        Integer numberOfDeaths,
        String reason,
        String report
) {
}

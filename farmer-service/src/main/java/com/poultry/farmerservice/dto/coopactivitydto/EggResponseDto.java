package com.poultry.farmerservice.dto.coopactivitydto;

import java.time.LocalDateTime;

public record EggResponseDto(
        Long id,
        LocalDateTime date,
        Long coopId,
        Integer numberOfEggsTrays,
        Integer numberOfExtraEggs,
        String reason
) {
}

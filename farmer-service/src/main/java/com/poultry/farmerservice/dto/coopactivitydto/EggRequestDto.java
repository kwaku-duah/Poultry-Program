package com.poultry.farmerservice.dto.coopactivitydto;

import java.time.LocalDateTime;

public record EggRequestDto(
        LocalDateTime date,
        Long coopId,
        Integer numberOfEggsTrays,
        Integer numberOfExtraEggs,
        String reason
) {
}

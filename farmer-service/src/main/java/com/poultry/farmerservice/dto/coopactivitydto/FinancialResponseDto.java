package com.poultry.farmerservice.dto.coopactivitydto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FinancialResponseDto(
        Long id,
        LocalDateTime date,
        Long coopId,
        String itemName,
        BigDecimal amount,
        String description
) {
}

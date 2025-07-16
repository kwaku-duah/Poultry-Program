package com.poultry.farmerservice.dto.coopactivitydto;

import com.poultry.farmerservice.entity.coupactivity.ActivityStatus;

import java.time.LocalDateTime;

public record ScheduleResponseDto(
        LocalDateTime date,
        Long coopId,
        String title,
        String description,
        LocalDateTime remindDate,
        ActivityStatus status
) {
}

package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.ScheduleRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    void addSchedule(Long farmerId, ScheduleRequestDto requestDto);
    List<ScheduleResponseDto> getSchedules(Long coopId, Long farmerId);
    void updateSchedule(Long id, Long coopId, Long farmerId, ScheduleRequestDto requestDto);
    void deleteSchedule(Long id, Long coopId, Long farmerId);
}

package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.ScheduleRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    void addSchedule(String farmerId, ScheduleRequestDto requestDto);
    List<ScheduleResponseDto> getSchedules(Long coopId, String farmerId);
    void updateSchedule(Long id, Long coopId, String farmerId, ScheduleRequestDto requestDto);
    void deleteSchedule(Long id, Long coopId, String farmerId);
}

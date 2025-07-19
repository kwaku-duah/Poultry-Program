package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.MortalityRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.MortalityResponseDto;

import java.util.List;

public interface MortalityService {
    void addMortalityRecord(Long farmerId, MortalityRequestDto requestDto);
    List<MortalityResponseDto> getMortalityRecords(Long coopId, Long farmerId);
    void updateMortalityRecord(Long id, Long coopId, Long farmerId, MortalityRequestDto requestDto);
    void deleteMortalityRecord(Long id, Long coopId, Long farmerId);
}


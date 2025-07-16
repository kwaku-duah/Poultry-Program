package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.MortalityRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.MortalityResponseDto;

import java.util.List;

public interface MortalityService {
    void addMortalityRecord(String farmerId, MortalityRequestDto requestDto);
    List<MortalityResponseDto> getMortalityRecords(Long coopId, String farmerId);
    void updateMortalityRecord(Long id, Long coopId, String farmerId, MortalityRequestDto requestDto);
    void deleteMortalityRecord(Long id, Long coopId, String farmerId);
}


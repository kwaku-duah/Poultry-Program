package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.EggRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.EggResponseDto;

import java.util.List;

public interface EggService {
    void addEggRecord(String farmerId, EggRequestDto eggRequestDto);

    List<EggResponseDto> getEggRecords(Long coopId, String farmerId);

    void updateEggRecord(Long id, Long coopId, String farmerId, EggRequestDto eggRequestDto);

    void deleteEggRecord(Long id, Long coopId, String farmerId);

}

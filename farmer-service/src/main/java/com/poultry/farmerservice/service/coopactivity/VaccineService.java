package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.VaccineRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.VaccineResponseDto;

import java.util.List;

public interface VaccineService {
    void addVaccineRecord(Long farmerId, VaccineRequestDto requestDto);
    List<VaccineResponseDto> getVaccineRecords(Long coopId, Long farmerId);
    void updateVaccineRecord(Long id, Long coopId, Long farmerId, VaccineRequestDto requestDto);
    void deleteVaccineRecord(Long id, Long coopId, Long farmerId);
}

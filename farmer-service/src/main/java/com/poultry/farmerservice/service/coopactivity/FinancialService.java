package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.FinancialRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.FinancialResponseDto;

import java.util.List;

public interface FinancialService {
    void addFinancialRecord(String farmerId, FinancialRequestDto requestDto);
    List<FinancialResponseDto> getFinancialRecords(Long coopId, String farmerId);
    void updateFinancialRecord(Long id, Long coopId, String farmerId, FinancialRequestDto requestDto);
    void deleteFinancialRecord(Long id, Long coopId, String farmerId);
}

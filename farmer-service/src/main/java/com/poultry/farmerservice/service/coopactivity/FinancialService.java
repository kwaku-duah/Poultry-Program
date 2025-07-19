package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.FinancialRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.FinancialResponseDto;

import java.util.List;

public interface FinancialService {
    void addFinancialRecord(Long farmerId, FinancialRequestDto requestDto);
    List<FinancialResponseDto> getFinancialRecords(Long coopId, Long farmerId);
    void updateFinancialRecord(Long id, Long coopId, Long farmerId, FinancialRequestDto requestDto);
    void deleteFinancialRecord(Long id, Long coopId, Long farmerId);
}

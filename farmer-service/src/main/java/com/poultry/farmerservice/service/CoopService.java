package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;

import java.util.List;

public interface CoopService {
    void addCoop(Long farmerId, CoopRequestDto coopRequestDto);
    List<CoopResponseDto> getAllCoops(Long farmerId);
    CoopResponseDto specificCoop(Long farmerId, Long id);
    void updateCoop(Long id,Long farmerId, CoopRequestDto coopRequestDto);
    void deleteCoop(Long id, Long farmerId);
}

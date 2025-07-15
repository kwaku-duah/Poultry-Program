package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;

import java.util.List;

public interface CoopService {
    void addCoop(String farmerId, CoopRequestDto coopRequestDto);
    List<CoopResponseDto> getAllCoops(String farmerId);
    CoopResponseDto specificCoop(String farmerId, Long id);
    void updateCoop(Long id,String farmerId, CoopRequestDto coopRequestDto);
    void deleteCoop(Long id, String farmerId);
}

package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;

import java.util.List;

public interface FarmService {
    void createFarmer(Long farmerId, FarmRequestDto farmRequestDto);
    List<FarmResponseDto> getAllFarmers(Long farmerId);
    void updateFarmer(Long farmerId, FarmRequestDto farmRequestDto);
    void deleteFarmer(Long farmerId);
}

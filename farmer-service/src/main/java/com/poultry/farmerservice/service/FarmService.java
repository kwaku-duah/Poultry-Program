package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;

import java.util.List;

public interface FarmService {
    void createFarmer(String farmerId, FarmRequestDto farmRequestDto);
    List<FarmResponseDto> getAllFarmers();
    void updateFarmer(String farmerId, FarmRequestDto farmRequestDto);
    void deleteFarmer(String farmerId);
}

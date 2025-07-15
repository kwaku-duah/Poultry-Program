package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;
import com.poultry.farmerservice.entity.Coop;

import java.util.List;

public interface CoopService {
    void addCoop(String farmerId, CoopRequestDto coopRequestDto);
    List<CoopResponseDto> getAllCoops(String farmerId);
}

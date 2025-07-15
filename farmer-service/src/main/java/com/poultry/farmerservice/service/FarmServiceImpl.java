package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;
import com.poultry.farmerservice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;

    @Override
    public void createFarmer(String farmerId, FarmRequestDto farmRequestDto) {

    }

    @Override
    public List<FarmResponseDto> getAllFarmers() {
        return List.of();
    }

    @Override
    public void updateFarmer(String farmerId, FarmRequestDto farmRequestDto) {

    }

    @Override
    public void deleteFarmer(String farmerId) {

    }
}

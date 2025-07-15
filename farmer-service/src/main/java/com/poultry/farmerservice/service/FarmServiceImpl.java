package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;
import com.poultry.farmerservice.entity.Farmer;
import com.poultry.farmerservice.exception.DuplicateResourceException;
import com.poultry.farmerservice.mapper.FarmMapper;
import com.poultry.farmerservice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public void createFarmer(String farmerId, FarmRequestDto farmRequestDto) {
       if (farmRepository.existsByFarmerId(farmerId)) {
           throw new DuplicateResourceException("Farmer with " + farmerId + " already exists");
       }

       Farmer farmer = farmMapper.toEntity(farmRequestDto);
       farmer.setFarmerId(farmerId);
       farmRepository.save(farmer);

    }

    @Override
    public List<FarmResponseDto> getAllFarmers() {
        return farmRepository.findAll().stream().map(farmMapper::farmResponseDto).toList();

    }

    @Override
    public void updateFarmer(String farmerId, FarmRequestDto farmRequestDto) {
        if (!farmRepository.existsByFarmerId(farmerId)) {

        }
    }

    @Override
    public void deleteFarmer(String farmerId) {

    }
}

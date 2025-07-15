package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;
import com.poultry.farmerservice.entity.Farmer;
import com.poultry.farmerservice.exception.DuplicateResourceException;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.FarmMapper;
import com.poultry.farmerservice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Transactional
    @Override
    public void createFarmer(String farmerId, FarmRequestDto farmRequestDto) {
       if (farmRepository.existsByFarmerId(farmerId)) {
           throw new DuplicateResourceException("Farmer with " + farmerId + " already exists");
       }

       Farmer farmer = farmMapper.toEntity(farmRequestDto);
       farmer.setFarmerId(farmerId);
       farmRepository.save(farmer);

    }

    @Transactional(readOnly = true)
    @Override
    public List<FarmResponseDto> getAllFarmers() {
        return farmRepository.findAll().stream().map(farmMapper::farmResponseDto).toList();

    }

    @Transactional
    @Override
    public void updateFarmer(String farmerId, FarmRequestDto farmRequestDto) {
        if (!farmRepository.existsByFarmerId(farmerId)) {
            throw new ResourceNotFoundException("Farmer with " + farmerId + " does not exist");
        }

        Farmer farmer = farmMapper.toEntity(farmRequestDto);
        farmer.setFarmerId(farmerId);
        farmRepository.save(farmer);
    }

    @Transactional
    @Override
    public void deleteFarmer(String farmerId) {
       if (farmRepository.existsByFarmerId(farmerId)) {
           throw new ResourceNotFoundException("Farmer with " + farmerId + " does not exist");
       }

       farmRepository.deleteByFarmerId(farmerId);

    }
}

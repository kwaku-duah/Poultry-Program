package com.poultry.farmerservice.service;

import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.Farmer;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.CoopMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoopServiceImpl implements CoopService {

    private final CoopRepository coopRepository;
    private final FarmRepository farmRepository;
    private final CoopMapper coopMapper;


    @Override
    public void addCoop(String farmerId, CoopRequestDto coopRequestDto) {
        Farmer farmer = farmRepository.findByFarmerId(farmerId)
                .orElseThrow(()-> new ResourceNotFoundException("Farmer with id " + farmerId + " not found"));

        int coopCount = coopRepository.coupCount_ByFarmerId(farmerId);
        String coopName = "Coop " + coopCount;
        Coop coop = coopMapper.toEntity(coopRequestDto);
        coop.setFarmer(farmer);
        coop.setCoopName(coopName);
        coopRepository.save(coop);
    }

    @Override
    public List<CoopResponseDto> getAllCoops(String farmerId) {

    }

    @Override
    public CoopResponseDto specificCoop(String farmerId, Long id) {
        return null;
    }

    @Override
    public void updateCoop(Long id, String farmerId, CoopRequestDto coopRequestDto) {

    }

    @Override
    public void deleteCoop(Long id, String farmerId) {

    }
}

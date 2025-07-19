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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoopServiceImpl implements CoopService {

    private final CoopRepository coopRepository;
    private final FarmRepository farmRepository;
    private final CoopMapper coopMapper;


    @Transactional
    @Override
    public void addCoop(Long farmerId, CoopRequestDto coopRequestDto) {
        Farmer farmer = farmRepository.findByFarmerId(farmerId)
                .orElseThrow(()-> new ResourceNotFoundException("Farmer with id " + farmerId + " not found"));

        int coopCount = coopRepository.countByFarmer_FarmerId(farmerId);

        String coopName = "Coop " + (coopCount + 1);
        Coop coop = coopMapper.toEntity(coopRequestDto);
        coop.setFarmer(farmer);
        coop.setCoopName(coopName);
        coopRepository.save(coop);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CoopResponseDto> getAllCoops(Long farmerId) {
        List<Coop> coops = coopRepository.findAllByFarmer_FarmerId(farmerId);
        return coops.stream().map(coopMapper::toCoopResponseDto).toList();

    }

    @Transactional(readOnly = true)
    @Override
    public CoopResponseDto specificCoop(Long farmerId, Long id) {
        Coop coop = coopRepository.findByIdAndFarmer_FarmerId(id,farmerId)
                .orElseThrow(()-> new ResourceNotFoundException("Coop with id " + id + " not found"));

        return coopMapper.toCoopResponseDto(coop);
    }

    @Transactional
    @Override
    public void updateCoop(Long id, Long farmerId, CoopRequestDto coopRequestDto) {
        Coop coop = coopRepository.findByIdAndFarmer_FarmerId(id, farmerId)
                .orElseThrow(()-> new ResourceNotFoundException("Coop with id " + id + " not found for update"));
        coop.setBreedName(coopRequestDto.breedName());
        coop.setBreedType(coopRequestDto.breedType());
        coop.setDateHatched(coopRequestDto.dateHatched());
        coop.setNumberOfBirds(coopRequestDto.numberOfBirds());
        coop.setUnitPrice(coopRequestDto.unitPrice());
        coopRepository.save(coop);
    }

    @Transactional
    @Override
    public void deleteCoop(Long id, Long farmerId) {
        Coop coop = coopRepository.findByIdAndFarmer_FarmerId(id, farmerId)
                .orElseThrow(()-> new ResourceNotFoundException("Farmer with id " + farmerId + " not found"));
        coopRepository.delete(coop);


    }
}

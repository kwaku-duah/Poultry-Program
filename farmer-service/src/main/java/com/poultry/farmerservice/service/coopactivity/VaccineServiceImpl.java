package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.VaccineRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.VaccineResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.coupactivity.VaccineRecord;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.coopactivitymapper.CoopActivityMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.VaccineRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRecordRepository vaccineRepo;
    private final CoopRepository coopRepo;
    private final CoopActivityMapper mapper;

    @Transactional
    @Override
    public void addVaccineRecord(Long farmerId, VaccineRequestDto dto) {
        Coop coop = coopRepo.findByIdAndFarmer_FarmerId(dto.coopId(), farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop not found"));
        VaccineRecord record = mapper.toVaccineEntity(dto);
        record.setCoop(coop);
        vaccineRepo.save(record);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VaccineResponseDto> getVaccineRecords(Long coopId, Long farmerId) {
        return vaccineRepo.findByCoop_IdAndCoop_Farmer_FarmerId(coopId, farmerId)
                .stream().map(mapper::toVaccineDto).toList();
    }

    @Transactional
    @Override
    public void updateVaccineRecord(Long id, Long coopId, Long farmerId, VaccineRequestDto dto) {
        VaccineRecord record = vaccineRepo.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine record not found"));
        record.setDate(dto.date());
        record.setVaccineName(dto.vaccineName());
        record.setVaccineType(dto.vaccineType());
        record.setDosage(dto.dosage());
        record.setDurationDosage(dto.durationDosage());
        record.setReason(dto.reason());
        record.setDescription(dto.description());
        vaccineRepo.save(record);
    }

    @Transactional
    @Override
    public void deleteVaccineRecord(Long id, Long coopId, Long farmerId) {
        VaccineRecord record = vaccineRepo.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine record not found"));
        vaccineRepo.delete(record);
    }
}

package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.FinancialRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.FinancialResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.coupactivity.FinancialRecord;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.coopactivitymapper.CoopActivityMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialServiceImpl implements FinancialService {
    private final FinancialRecordRepository financialRecordRepository;
    private final CoopRepository coopRepository;
    private final CoopActivityMapper coopMapper;

    @Transactional
    @Override
    public void addFinancialRecord(Long farmerId, FinancialRequestDto requestDto) {
        Coop coop = coopRepository.findByIdAndFarmer_FarmerId(requestDto.coopId(), farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop not found for farmer: " + farmerId));
        FinancialRecord record = coopMapper.toFinancialEntity(requestDto);
        record.setCoop(coop);
        financialRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FinancialResponseDto> getFinancialRecords(Long coopId, Long farmerId) {
        return financialRecordRepository.findByCoop_IdAndCoop_Farmer_FarmerId(coopId, farmerId)
                .stream()
                .map(coopMapper::toFinancialDto)
                .toList();
    }

    @Transactional
    @Override
    public void updateFinancialRecord(Long id, Long coopId, Long farmerId, FinancialRequestDto requestDto) {
        FinancialRecord record = financialRecordRepository.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Financial record not found"));
        record.setDate(requestDto.date());
        record.setAmount(requestDto.amount());
        record.setItemName(requestDto.itemName());
        record.setDescription(requestDto.description());
        financialRecordRepository.save(record);
    }

@Transactional
    @Override
    public void deleteFinancialRecord(Long id, Long coopId, Long farmerId) {
        FinancialRecord record = financialRecordRepository.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Financial record not found"));
        financialRecordRepository.delete(record);
    }
}

package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.coupactivity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByCoop_IdAndCoop_Farmer_FarmerId(Long coopId, Long farmerId);

    Optional<FinancialRecord> findByIdAndCoop_IdAndCoop_Farmer_FarmerId(Long id, Long coopId, Long farmerId);
}

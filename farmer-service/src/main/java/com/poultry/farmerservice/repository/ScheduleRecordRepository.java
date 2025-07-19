package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.coupactivity.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, Long> {
    List<ScheduleRecord> findByCoop_IdAndCoop_Farmer_FarmerId(Long coopId, Long farmerId);
    Optional<ScheduleRecord> findByIdAndCoop_IdAndCoop_Farmer_FarmerId(Long id, Long coopId, Long farmerId);
}

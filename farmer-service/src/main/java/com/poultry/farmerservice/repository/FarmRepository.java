package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farmer,Long> {
    boolean existsByFarmerId(Long farmerId);
    Optional<Farmer> findByFarmerId(Long farmerId);
    List<Farmer> findAllByFarmerId(Long farmerId);
    void deleteByFarmerId(Long farmerId);
}

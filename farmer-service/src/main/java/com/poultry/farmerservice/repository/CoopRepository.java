package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.Coop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoopRepository extends JpaRepository<Coop, Long> {
    int coupCount_ByFarmerId(String farmerId);
    Optional<Coop> findByIdAndFarmerId_FarmerId(Long id, String farmerId);
    List<Coop> findByFarmer_FarmerId(String farmerId);


}

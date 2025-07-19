package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.Coop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoopRepository extends JpaRepository<Coop, Long> {
    int countByFarmer_FarmerId(Long farmerId);
    Optional<Coop> findByIdAndFarmer_FarmerId(Long id, Long farmerId);
    List<Coop> findAllByFarmer_FarmerId(Long farmerId);


}

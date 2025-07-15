package com.poultry.farmerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "coops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String breedName;

    @Enumerated(EnumType.STRING)
    private BreedType breedType;

    private LocalDate dateHatched;

    private Integer numberOfBirds;

    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", referencedColumnName = "farmerId")
    private Farmer farmer;


}

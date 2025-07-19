package com.poultry.farmerservice.entity;

import com.poultry.farmerservice.entity.coupactivity.CoupActivity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String coopName;

    @Column(nullable = false)
    private String breedName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BreedType breedType;

    @Column(nullable = false)
    private LocalDate dateHatched;

    @Column(nullable = false)
    private Integer numberOfBirds;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", referencedColumnName = "farmerId")
    private Farmer farmer;

    @OneToMany(mappedBy = "coop", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoupActivity> coopActivities = new ArrayList<>();


}

package com.poultry.farmerservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "farmers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String farmerId;

    @Column(nullable = false)
    private String farmName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String gpsAddress;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coop> coops;
}

package com.poultry.farmerservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String farmerId;

    private String farmName;

    private String phoneNumber;

    private String location;

    private String GPSAddress;

    private List<Coop> coops;
}

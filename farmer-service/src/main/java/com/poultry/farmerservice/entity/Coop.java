package com.poultry.farmerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private BreedType breedType;
}

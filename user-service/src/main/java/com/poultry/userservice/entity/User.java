package com.poultry.userservice.entity;

import lombok.*;

@Entity
@Table(name= "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GeneratedType.IDENTITY)
    private Long id;
    private String email;
    private String password;
}

package com.poultry.farmerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class FarmerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmerServiceApplication.class, args);
    }

}

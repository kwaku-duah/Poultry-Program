package com.poultry.gatewayservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/see")
public class WorkController {

    @GetMapping
    public String testControl() {
        return "Testing to see if it works, this is great";
    }
}

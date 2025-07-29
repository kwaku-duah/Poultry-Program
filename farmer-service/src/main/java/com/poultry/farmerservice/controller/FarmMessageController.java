package com.poultry.farmerservice.controller;

import com.poultry.farmerservice.client.MessageServiceGrpcClient;
import com.poultry.farmerservice.grpc.MessageRequest;
import com.poultry.farmerservice.grpc.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/farmers")
@RequiredArgsConstructor
public class FarmMessageController {
    private final MessageServiceGrpcClient messageServiceGrpcClient;

    @PostMapping("/messages")
    public MessageResponse sendMessage(@RequestBody MessageRequest request) {
        return messageServiceGrpcClient.sendMessage(request);
    }
}

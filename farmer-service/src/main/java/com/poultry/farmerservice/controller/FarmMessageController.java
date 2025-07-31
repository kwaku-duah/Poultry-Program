package com.poultry.farmerservice.controller;

import com.poultry.farmerservice.client.MessageServiceGrpcClient;
import com.poultry.farmerservice.dto.kafka.KafkaMesageRequestDto;
import com.poultry.farmerservice.dto.kafka.KafkaResponseDto;
import com.poultry.farmerservice.grpc.MessageRequest;
import com.poultry.farmerservice.grpc.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/farmers")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN, 'FARMER')")
public class FarmMessageController {
    private final MessageServiceGrpcClient messageServiceGrpcClient;

    @PostMapping(value = "/messages", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KafkaResponseDto> sendMessage(@RequestBody KafkaMesageRequestDto dto) {
        MessageRequest request = MessageRequest.newBuilder()
                .setSenderId(dto.senderId())
                .setReceiverId(dto.receiverId())
                .setContent(dto.content())
                .build();

        MessageResponse grpcResponse = messageServiceGrpcClient.sendMessage(request);
        KafkaResponseDto response = new KafkaResponseDto(
                grpcResponse.getSenderId(),
                grpcResponse.getReceiverId(),
                grpcResponse.getContent()
        );
        return ResponseEntity.ok(response);
    }
}
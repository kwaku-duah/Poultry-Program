package com.poultry.farmerservice.dto.kafka;

public record KafkaMesageRequestDto(
        String senderId,
        String receiverId,
        String content
) {
}

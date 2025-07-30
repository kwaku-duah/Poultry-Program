package com.poultry.farmerservice.dto.kafka;

public record KafkaResponseDto(
        String senderId,
        String receiverId,
        String content
) {
}

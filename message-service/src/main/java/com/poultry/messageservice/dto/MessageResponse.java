package com.poultry.messageservice.dto;

public record MessageResponse(
        String senderId,
        String receiverId,
        String content
) {
}

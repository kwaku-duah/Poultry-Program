package com.poultry.messageservice.dto;

public record MessageRequest(
        String senderId, String receiverId, String content
) {
}

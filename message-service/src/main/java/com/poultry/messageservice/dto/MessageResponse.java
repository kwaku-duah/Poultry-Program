package com.poultry.messageservice.dto;

import java.time.LocalDateTime;

public record MessageResponse(
        String senderId,
        String receiverId,
        String content,
        LocalDateTime timestamp
) {
}

package com.poultry.messageservice.controller;

import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;
import com.poultry.messageservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@PreAuthorize("hasAnyRole('FARMER', 'VET', 'SUPPLIER', 'ADMIN')")
public class StompController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat.personalChat")
    public void personalChat(@Payload MessageRequest messageRequest,
                             SimpMessageHeaderAccessor headerAccessor) {
        if (messageRequest == null || messageRequest.senderId() == null || messageRequest.receiverId() == null) {
            return;
        }

        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        if (userId == null || !userId.equals(messageRequest.senderId())) {
            return;
        }

        try {

            chatService.chatUser(messageRequest);

            simpMessagingTemplate.convertAndSendToUser(
                    messageRequest.receiverId(),
                    "/queue/messages",
                    messageRequest
            );

            simpMessagingTemplate.convertAndSendToUser(
                    messageRequest.receiverId(),
                    "/queue/notifications",
                    "New message from " + messageRequest.senderId()
            );
        } catch (Exception e) {
            System.err.println("Failed to process personal chat: " + e.getMessage());
        }
    }

    @GetMapping("/history/{senderId}/{receiverId}")
    public ResponseEntity<List<MessageResponse>> fetchMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("receiverId") String receiverId) {
        if (senderId == null || receiverId == null) {
            return ResponseEntity.badRequest().build();
        }

        List<MessageResponse> messages = chatService.getAllMessages(senderId, receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
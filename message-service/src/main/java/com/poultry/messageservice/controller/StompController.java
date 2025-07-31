package com.poultry.messageservice.controller;


import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;
import com.poultry.messageservice.service.ChatService;
import com.poultry.messageservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notify")
@PreAuthorize("hasAnyRole('ADMIN', 'VET', 'SUPPLIER')")
public class StompController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat.personalChat")
    public void personalChat(@Payload MessageRequest messageRequest) {
        chatService.chatUser(messageRequest);
        simpMessagingTemplate.convertAndSendToUser(
                messageRequest.receiverId()
        ,"/queue/messages", messageRequest);

        simpMessagingTemplate.convertAndSendToUser(
                messageRequest.receiverId()
                ,"/queue/messages", messageRequest.senderId());
    }
    
    @GetMapping()
    public ResponseEntity<List<MessageResponse>> fetchMessages(
            @PathVariable String senderId, @PathVariable String receiverId) {
        return new ResponseEntity<>(chatService.getAllMessages(senderId, receiverId), HttpStatus.OK);
    }
}

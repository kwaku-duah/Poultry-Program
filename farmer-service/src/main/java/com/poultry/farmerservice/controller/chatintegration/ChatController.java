package com.poultry.farmerservice.controller.chatintegration;

import com.poultry.farmerservice.payload.ChatRequest;
import com.poultry.farmerservice.payload.ChatResponse;
import com.poultry.farmerservice.service.chatintegration.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@PreAuthorize("hasRole('FARMER')")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> sendChat(@RequestBody ChatRequest chatRequest) {
        ChatResponse response = chatService.sendMessage(chatRequest);
        return ResponseEntity.ok(response);
    }
}

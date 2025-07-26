package com.poultry.messageservice.controller;


import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;
import com.poultry.messageservice.payload.ApiResponse;
import com.poultry.messageservice.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('FARMER', 'VET', 'SUPPLIER')")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> saveMessage(@RequestBody MessageRequest message) {
        messageService.saveMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable String senderId, @PathVariable String receiverId) {
        return new ResponseEntity<>(messageService.getMessages(senderId, receiverId), HttpStatus.OK);
    }
}
package com.poultry.messageservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.service.MessageService;
import com.poultry.messageservice.websocket.WebSocketMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(topics = "message-topic", groupId = "messaging")
    public void listen(String messageJson) {
        try {
            // Deserialize the incoming JSON into a MessageRequest
            MessageRequest messageRequest = objectMapper.readValue(messageJson, MessageRequest.class);
            log.info("Received Kafka message: {}", messageRequest);

            // Save the message
            messageService.saveMessage(messageRequest);

            // Send original messageJson (or optionally a formatted response) via WebSocket
            for (WebSocketSession session : WebSocketMessageHandler.getWebSocketSessions()) {
                try {
                    session.sendMessage(new TextMessage(messageJson));
                } catch (IOException e) {
                    log.error("Error sending message to WebSocket session: {}", e.getMessage());
                }
            }

        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize Kafka message", e);
        }
    }}

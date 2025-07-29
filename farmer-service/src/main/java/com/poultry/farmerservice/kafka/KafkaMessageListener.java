package com.poultry.farmerservice.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poultry.farmerservice.grpc.MessageRequest;
import com.poultry.farmerservice.websocketconfiguration.WebSocketMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@Service
public class KafkaMessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "message-topic", groupId = "farmer-group")
    public void listen(String messageJson) {
        try {
            MessageRequest message = objectMapper.readValue(messageJson, MessageRequest.class);
            log.info("💬 New message received: From: {}, To: {}, Content: {}",
                    message.getSenderId(), message.getReceiverId(), message.getContent());
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
    }
}
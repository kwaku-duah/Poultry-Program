package com.poultry.farmerservice.service.chatintegration;

import com.poultry.farmerservice.payload.ChatRequest;
import com.poultry.farmerservice.payload.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final WebClient webClient;

    @Value("${huggingface.chat.api.url}")
    private String apiUrl;

    @Value("${huggingface.model}")
    private String model;

    @SuppressWarnings("unchecked")
    @Override
    public ChatResponse sendMessage(ChatRequest chatRequest) {
        log.info("Sending chat request: {}", chatRequest);

        Map<String, Object> requestPayload = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a poultry farming expert."),
                        Map.of("role", "user", "content", chatRequest.getMessage())
                ),
                "max_new_tokens", 128
        );

        try {
            Map<String, Object> response = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + System.getenv("HUGGINGFACE_API_KEY"))
                    .bodyValue(requestPayload)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            String reply = extractReply(response);
            return new ChatResponse(reply);
        } catch (Exception e) {
            log.error("Error calling Hugging Face chat API: {}", e.getMessage());
            return new ChatResponse("Error generating response: " + e.getMessage());
        }
    }

    private String extractReply(Map<String, Object> response) {
        try {
            Object choicesObj = response.get("choices");
            if (choicesObj instanceof List<?> choices && !choices.isEmpty()) {
                Object firstChoice = choices.getFirst();
                if (firstChoice instanceof Map<?, ?> choice) {
                    Object message = choice.get("message");
                    if (message instanceof Map<?, ?> messageMap) {
                        Object content = messageMap.get("content");
                        if (content instanceof String) {
                            return (String) content;
                        }
                    }
                }
            }
            return "No response generated";
        } catch (Exception e) {
            log.error("Error parsing Hugging Face response: {}", e.getMessage());
            return "Error parsing response: " + e.getMessage();
        }
    }
}
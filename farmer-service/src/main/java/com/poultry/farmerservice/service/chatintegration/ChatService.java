package com.poultry.farmerservice.service.chatintegration;

import com.poultry.farmerservice.payload.ChatRequest;
import com.poultry.farmerservice.payload.ChatResponse;

public interface ChatService {
    ChatResponse sendMessage(ChatRequest chatRequest);
}

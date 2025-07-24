package com.poultry.farmerservice.service.chatintegration;


import com.poultry.farmerservice.payload.ChatRequest;
import com.poultry.farmerservice.payload.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    @Override
    public ChatResponse sendMessage(ChatRequest chatRequest) {
        return null;
    }
}

package com.poultry.farmerservice.client;

import com.poultry.farmerservice.grpc.MessageFarmerGrpc;
import com.poultry.farmerservice.grpc.MessageRequest;
import com.poultry.farmerservice.grpc.MessageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceGrpcClient {
    private final MessageFarmerGrpc.MessageFarmerBlockingStub blockingStub;

    public MessageServiceGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9093)
                .usePlaintext()
                .build();
        this.blockingStub = MessageFarmerGrpc.newBlockingStub(channel);
    }

    public MessageResponse sendMessage(MessageRequest messageRequest) {
        return blockingStub.sendMessage(messageRequest);
    }
}

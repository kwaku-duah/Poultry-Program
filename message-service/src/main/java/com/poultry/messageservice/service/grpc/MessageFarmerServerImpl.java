package com.poultry.messageservice.service.grpc;



import com.poultry.messageservice.grpc.MessageFarmerGrpc;
import com.poultry.messageservice.grpc.MessageRequest;
import com.poultry.messageservice.grpc.MessageResponse;
import com.poultry.messageservice.service.MessageService;
import com.poultry.messageservice.service.kafka.KafkaService;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;



@GrpcService
public class MessageFarmerServerImpl extends MessageFarmerGrpc.MessageFarmerImplBase {
    private final MessageService messageFarmer;
    private final KafkaService kafkaService;

    public MessageFarmerServerImpl(MessageService messageFarmer, KafkaService kafkaService) {
        this.messageFarmer = messageFarmer;
        this.kafkaService = kafkaService;
    }

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {


        com.poultry.messageservice.dto.MessageRequest dto = new com.poultry.messageservice.dto.MessageRequest(
                request.getSenderId(),
                request.getReceiverId(),
                request.getContent()
        );
        messageFarmer.saveMessage(dto);
        kafkaService.sendMessage(dto);

        MessageResponse response = MessageResponse.newBuilder()
                .setSenderId(request.getSenderId())
                .setReceiverId(request.getReceiverId())
                .setContent(request.getContent())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}

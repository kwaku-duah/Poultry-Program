package com.poultry.messageservice.service.grpc;



import com.poultry.messageservice.grpc.MessageRequest;
import com.poultry.messageservice.grpc.MessageResponse;
import com.poultry.messageservice.grpc.MessageServiceGrpc;
import com.poultry.messageservice.service.MessageService;
import com.poultry.messageservice.service.kafka.KafkaService;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;



@GrpcService
public class MessageServiceServer extends MessageServiceGrpc.MessageServiceImplBase {
    private final MessageService messageService;
    private final KafkaService kafkaService;

    public MessageServiceServer(MessageService messageService, KafkaService kafkaService) {
        this.messageService = messageService;
        this.kafkaService = kafkaService;
    }

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {


        com.poultry.messageservice.dto.MessageRequest dto = new com.poultry.messageservice.dto.MessageRequest(
                request.getSenderId(),
                request.getReceiverId(),
                request.getContent()
        );
        messageService.saveMessage(dto);
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

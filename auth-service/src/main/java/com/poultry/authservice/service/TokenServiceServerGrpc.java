package com.poultry.authservice.service;

import com.poultry.token.TokenValidatorGrpc;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class TokenServiceServerGrpc extends TokenValidatorGrpc.TokenValidatorImplBase {

}

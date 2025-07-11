package com.poultry.userservice.exception;

import com.poultry.userservice.Payload.GeneralErrorResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponseHandler> errorHandler(Exception ex) {
       GeneralErrorResponseHandler handler = new GeneralErrorResponseHandler("Something went wrong, please contact suport", HttpStatus.INTERNAL_SERVER_ERROR.value());
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(handler);
    }
}

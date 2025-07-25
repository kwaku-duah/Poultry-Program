package com.poultry.messageservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poultry.messageservice.payload.GeneralExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralExceptionHandler> generalExceptionHandler(Exception ex) {
        GeneralExceptionHandler response = new GeneralExceptionHandler("Please contact administrator", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

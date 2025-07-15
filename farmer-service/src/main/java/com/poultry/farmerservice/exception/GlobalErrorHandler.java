package com.poultry.farmerservice.exception;

import com.poultry.farmerservice.payload.GeneralErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> genericErrorHandler(Exception ex) {
        ex.printStackTrace();
        GeneralErrorResponse response = new GeneralErrorResponse("Something went wrong, contact support", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<GeneralErrorResponse> duplicateErrorHandler(DuplicateResourceException ex) {
        GeneralErrorResponse response = new GeneralErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GeneralErrorResponse> notFoundErrorHandler(ResourceNotFoundException ex) {
        GeneralErrorResponse response = new GeneralErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

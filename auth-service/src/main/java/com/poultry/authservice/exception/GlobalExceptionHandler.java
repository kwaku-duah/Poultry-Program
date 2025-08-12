package com.poultry.authservice.exception;

import com.poultry.authservice.payload.GenericErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorResponse> handleGenericError(Exception ex) {
        System.out.println(ex.getMessage());
        GenericErrorResponse response = new GenericErrorResponse("Something went wrong, please contact support", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<GenericErrorResponse> invalidCredentials(InvalidCredentialException ex) {
        GenericErrorResponse response = new GenericErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericErrorResponse> userNotFound(UserNotFoundException ex) {
        GenericErrorResponse response = new GenericErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotDefaultRegistrantException.class)
    public ResponseEntity<GenericErrorResponse> notDefaultMethod(NotDefaultRegistrantException ex) {
        GenericErrorResponse response = new GenericErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

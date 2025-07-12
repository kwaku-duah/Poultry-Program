package com.poultry.authservice.payload;

public record GenericErrorResponse(String message, int statusCode) {
}

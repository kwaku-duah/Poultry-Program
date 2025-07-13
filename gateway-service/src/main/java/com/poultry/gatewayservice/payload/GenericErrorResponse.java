package com.poultry.gatewayservice.payload;

public record GenericErrorResponse(String message, int statusCode) {
}

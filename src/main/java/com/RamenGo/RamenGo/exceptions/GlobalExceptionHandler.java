package com.RamenGo.RamenGo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final HttpHeaders headers = new HttpHeaders();

    static {
        headers.add("X-Status-Message", "");
    }
    @ExceptionHandler(ApiKeyMissingException.class)
    public ResponseEntity<ErrorResponse> handleApiKeyMissingException(ApiKeyMissingException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        headers.set("X-Status-Message", "Forbidden");

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .headers(headers)
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidOrderRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderRequestException(InvalidOrderRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Status-Message", "Invalid request");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .body(errorResponse);
    }

    @ExceptionHandler(OrderPlacementException.class)
    public ResponseEntity<ErrorResponse> handleOrderPlacementException(OrderPlacementException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Status-Message", "Internal server error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(headers)
                .body(errorResponse);
    }

    @Setter
    @Getter
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

    }
}

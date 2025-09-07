package com.tutorial.validation.exceptions.advisors;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;


public record FormErrorResponse(
        Instant timestamp,
        HttpStatus status, // HTTP status code
        String error, // e.g. "Bad Request"
        String message, // general message
        String path,
        List<FormFieldErrorResponse> messages
) {

    record FormFieldErrorResponse(String field, String message) {
    }
}


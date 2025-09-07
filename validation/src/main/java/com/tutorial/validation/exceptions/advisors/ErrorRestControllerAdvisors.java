package com.tutorial.validation.exceptions.advisors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ErrorRestControllerAdvisors {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FormErrorResponse> handleFormValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        var fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FormErrorResponse.FormFieldErrorResponse(err.getField(), err.getDefaultMessage()))
                .toList();


        return ResponseEntity.badRequest().body(new FormErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                "Some fields are invalid",
                request.getRequestURI(),
                fieldErrors
        ));
    }
}

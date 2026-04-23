package com.ws101.fortuna.EcommerceApi.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Validation Failed");
        error.put("message", ex.getBindingResult().getFieldError().getDefaultMessage());
        error.put("status", 400);
        error.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // OPTIONAL: your other handler (separate method)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Bad Request");
        error.put("message", "Invalid input");
        error.put("status", 400);
        error.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
package com.xynerotech.task.household_services_booking_platform.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    //exception handler for ResourceNotFoundException
    @ExceptionHandler(value = ResourceNotFoundException.class )
    public ErrorResponse handlerResourceNotFoundException(ResourceNotFoundException exception){

        return new ErrorResponse(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value());

    }

    //exception handler for MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        // Class-level (global) errors
        ex.getBindingResult().getGlobalErrors().forEach(error -> {
            errors.put("error", error.getDefaultMessage());
        });

        response.put("message", "Validation Failed");
        response.put("errors", errors);
        response.put("timestamp", LocalDateTime.now());
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
        response.put("success", false);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    //exception handler for other exception
    @ExceptionHandler(value = Exception.class )
    public ErrorResponse handlerException(Exception exception){

        return new ErrorResponse(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value());

    }


}

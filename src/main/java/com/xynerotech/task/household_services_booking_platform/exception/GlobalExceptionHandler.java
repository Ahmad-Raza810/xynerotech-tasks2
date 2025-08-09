package com.xynerotech.task.household_services_booking_platform.exception;

import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        // 🔹 Field-level errors (e.g., @NotBlank, @Email)
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // 🔹 Class-level errors (e.g., @AtLeastOneFieldNotEmpty)
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put("globalError", error.getDefaultMessage())  // key: fixed or customize it
        );

        response.put("success", false);
        response.put("message", "Validation Failed");
        response.put("errors", errors);
        response.put("timestamp", LocalDateTime.now());
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();

        String message;
        if (cause instanceof DateTimeParseException) {
            message = "Invalid date format. Please use dd-mm-yy.";
        } else {
            message = "Invalid request body. Please check your input.";
        }

        ErrorResponse error = new ErrorResponse(
                message,
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




    // Handler for DuplicateResourceException
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailExistsException(DuplicateResourceException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                ex.getMessage(),
                LocalDateTime.now(),
                null,
                false,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handler for InvalidCredentialsException
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                ex.getMessage(),
                LocalDateTime.now(),
                null,
                false,
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Handler for InvalidBookingDateException exception
    @ExceptionHandler(InvalidBookingDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBookingDateException(InvalidBookingDateException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handler for InvalidBookingDateException exception
    @ExceptionHandler(BookingConflictException.class)
    public ResponseEntity<ErrorResponse> handleBookingConflictException(BookingConflictException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Something went wrong. Please try again.",
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

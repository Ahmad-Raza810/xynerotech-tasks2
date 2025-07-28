package com.xynerotech.task.household_services_booking_platform.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>{
    private String message;
    private LocalDateTime timestamp;
    private T data;
    private boolean success;
    private int statusCode;
}

package com.xynerotech.task.household_services_booking_platform.exception;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private int statuscode;
}

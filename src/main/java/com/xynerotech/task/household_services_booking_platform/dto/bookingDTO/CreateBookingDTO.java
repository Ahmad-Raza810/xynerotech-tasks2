package com.xynerotech.task.household_services_booking_platform.dto.bookingDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xynerotech.task.household_services_booking_platform.validation.ValidLocalTime;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.*;


@Data
public class CreateBookingDTO {

    @NotNull(message = "Service ID is required")
    private Long serviceId;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Booking date must be today or in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "Time is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @ValidLocalTime
    private LocalTime time;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;
}


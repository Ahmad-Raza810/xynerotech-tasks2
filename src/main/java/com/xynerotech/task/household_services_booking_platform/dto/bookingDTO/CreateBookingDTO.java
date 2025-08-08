package com.xynerotech.task.household_services_booking_platform.dto.bookingDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xynerotech.task.household_services_booking_platform.entities.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDTO {


    @NotNull(message = "user id is required.")
    private Long userId;

    @NotNull(message = "service id is required.")
    private Long serviceId;

    @NotNull(message = "date is required.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

}

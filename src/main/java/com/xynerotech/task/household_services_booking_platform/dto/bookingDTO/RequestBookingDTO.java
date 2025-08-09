package com.xynerotech.task.household_services_booking_platform.dto.bookingDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookingDTO {


    @NotNull(message = "service id is required.")
    private Long serviceId;

    @NotNull(message = "date is required in (dd-mm-yy) format.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

}

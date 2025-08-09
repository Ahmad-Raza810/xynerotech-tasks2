package com.xynerotech.task.household_services_booking_platform.controller;


import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.RequestBookingDTO;
import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.ResponseBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/user")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("/{userid}/bookings/add")
    public ResponseEntity<ApiResponse<ResponseBookingDTO>> addBooking(@PathVariable Long userid,@Valid @RequestBody RequestBookingDTO requestBookingDTO) {
        Booking booking = bookingService.addBooking(userid,requestBookingDTO);
        ResponseBookingDTO responseBookingDTO = ResponseBookingDTO.bookingToDto(booking);

        ApiResponse<ResponseBookingDTO> response = new ApiResponse<>(
                "Booking successfully added",
                LocalDateTime.now(),
                responseBookingDTO,
                true,
                HttpStatus.CREATED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

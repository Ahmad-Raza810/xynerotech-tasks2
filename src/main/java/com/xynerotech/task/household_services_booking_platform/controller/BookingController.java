package com.xynerotech.task.household_services_booking_platform.controller;


import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.CreateBookingDTO;
import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.ResponseBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    //Route for making a booking
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{userid}/book")
    public ResponseEntity<ApiResponse<ResponseBookingDTO>> addBooking(@PathVariable Long userid, @Valid @RequestBody CreateBookingDTO bookingDTO) {
        Booking booking = bookingService.addBooking(userid, bookingDTO);
        ResponseBookingDTO responseBookingDTO = ResponseBookingDTO.bookingToDto(booking);

        ApiResponse<ResponseBookingDTO> response = new ApiResponse<>(
                "Booking  confirmed..",
                LocalDateTime.now(),
                responseBookingDTO,
                true,
                HttpStatus.CREATED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //Route for "Get My booking"
    @GetMapping("/{userid}/my-bookings")
    public ResponseEntity<ApiResponse<List<ResponseBookingDTO>>> getMyBooking(
            @PathVariable("userid") Long userId,
            Authentication authentication) {

        List<Booking> bookings=bookingService.getBookingsByUserId(userId,authentication);

        List<ResponseBookingDTO> bookingDTOS = bookings
                .stream()
                .map(ResponseBookingDTO::bookingToDto)
                .toList();

        String message = bookingDTOS.isEmpty() ? "There is no bookings." : "Booking fetched successfully.";

        ApiResponse<List<ResponseBookingDTO>> response = new ApiResponse<>(
                message,
                LocalDateTime.now(),
                bookingDTOS,
                true,
                HttpStatus.OK.value()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

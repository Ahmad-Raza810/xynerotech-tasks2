package com.xynerotech.task.household_services_booking_platform.controller;


import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.ResponseBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/bookings") // Admin route
public class BookingAdminController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResponseBookingDTO>>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        List<ResponseBookingDTO> dtos = bookings
                .stream()
                .map(ResponseBookingDTO::bookingToDto)
                .toList();

        ApiResponse<List<ResponseBookingDTO>> response = new ApiResponse<>(
                "All bookings fetched successfully",
                LocalDateTime.now(),
                dtos,
                true,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
    }
}


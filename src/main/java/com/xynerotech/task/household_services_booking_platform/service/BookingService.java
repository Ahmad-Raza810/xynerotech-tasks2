package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.CreateBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookingService {

    Booking addBooking(Long userId, CreateBookingDTO bookingDTO);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByUserId(Long userId,Authentication authentication);





}

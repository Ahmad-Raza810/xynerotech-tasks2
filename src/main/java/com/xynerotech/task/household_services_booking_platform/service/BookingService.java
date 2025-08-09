package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.RequestBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;

public interface BookingService {

    Booking addBooking(Long userId,RequestBookingDTO bookingDTO);




}

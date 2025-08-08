package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.CreateBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;

public interface BookingService {

    Booking addBooking(CreateBookingDTO bookingDTO);


}

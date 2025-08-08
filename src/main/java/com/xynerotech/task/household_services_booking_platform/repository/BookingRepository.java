package com.xynerotech.task.household_services_booking_platform.repository;

import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository  extends JpaRepository<Booking,Long> {
}

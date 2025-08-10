package com.xynerotech.task.household_services_booking_platform.repository;

import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking,Long> {

    boolean existsByUserAndServiceAndBookingDate(AppUser user, HomeService service, LocalDate bookingDate);

    List<Booking> findByUserUserId(Long userId);

}

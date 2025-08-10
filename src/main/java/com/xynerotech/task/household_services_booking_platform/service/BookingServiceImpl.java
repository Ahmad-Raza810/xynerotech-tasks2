package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.CreateBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.*;
import com.xynerotech.task.household_services_booking_platform.exception.BookingConflictException;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.repository.BookingRepository;
import com.xynerotech.task.household_services_booking_platform.repository.HomeServiceRepository;
import com.xynerotech.task.household_services_booking_platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HomeServiceRepository homeServiceRepository;

    @Autowired
    private UserRepository userRepository;


    //service method for make a booking
    @Override
    public Booking addBooking(Long userId, CreateBookingDTO bookingDTO) {

        // Check user exists
        AppUser user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id '" + userId + "' not exists."));

        // Check service exists
        HomeService service = homeServiceRepository
                .findById(bookingDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service with id '" + bookingDTO.getServiceId() + "' not exists."));

        // Check booking conflict
        if (bookingRepository.existsByUserAndServiceAndBookingDate(user, service, bookingDTO.getDate())) {
            throw new BookingConflictException("You already have a booking for this service on the given date.");
        }

        // Create booking entity
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setBookingDate(bookingDTO.getDate());
        booking.setTime(bookingDTO.getTime());
        booking.setAddress(bookingDTO.getAddress());

        return bookingRepository.save(booking);
    }


    //service method get all bookings (Admin only route)
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId, Authentication authentication) {
        // 1. Get logged-in user's email from authentication
        String email = authentication.getName();

        // 2. Get user from email
        AppUser user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 3. If not admin, ensure they are accessing their own bookings
        if (user.getRole() != Role.ADMIN && !user.getUserId().equals(userId)) {
            throw new AccessDeniedException("You are not authorized to access these bookings");
        }

        // 4. Return bookings for the authorized user or admin
        return bookingRepository.findByUserUserId(userId);
    }


}

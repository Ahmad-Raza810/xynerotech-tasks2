package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.RequestBookingDTO;
import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.ResponseBookingDTO;
import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import com.xynerotech.task.household_services_booking_platform.entities.BookingStatus;
import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import com.xynerotech.task.household_services_booking_platform.exception.BookingConflictException;
import com.xynerotech.task.household_services_booking_platform.exception.InvalidBookingDateException;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.repository.BookingRepository;
import com.xynerotech.task.household_services_booking_platform.repository.HomeServiceRepository;
import com.xynerotech.task.household_services_booking_platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Slf4j
@Service
@Transactional
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HomeServiceRepository homeServiceRepository;

    @Autowired
    private UserRepository userRepository;



    //service method for make a booking
    @Override
    public Booking addBooking(Long userId,RequestBookingDTO bookingDTO) {

        //checking user exists or not.
       AppUser user=userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user with id '"+userId+"' not exists."));

        //checking service exists or not.
       HomeService service=homeServiceRepository
               .findById(bookingDTO.getServiceId())
               .orElseThrow(()->new ResourceNotFoundException("service with id '"+bookingDTO.getServiceId()+"' not exists."));

       //checking date is not from past.
        ZoneId userZone = ZoneId.of("Asia/Kolkata");
        LocalDate todayInUserZone = LocalDate.now(userZone);

        // checking user have same booking(same service and user)   at same date or not.
        if (bookingDTO.getDate().isBefore(todayInUserZone)) {
            throw new InvalidBookingDateException("Date must not be in the past");
        }

        if(bookingRepository.existsByUserAndServiceAndBookingDate(user,service,bookingDTO.getDate()))
        {
            throw new BookingConflictException("You have already following booking at same date you mentioned.");
        }


        log.info("Creating booking for userId={}, serviceId={}, date={}",
                userId,
                bookingDTO.getServiceId(),
                bookingDTO.getDate());


        //creating booking object and setting up values
        Booking booking=new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setBookingDate(bookingDTO.getDate());
        booking.setStatus(BookingStatus.PENDING);

       return  bookingRepository.save(booking);
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
        AppUser user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 3. Check if requested userid matches logged-in user's id
        if (!user.getUserId().equals(userId)) {
            // Forbidden if trying to access other user's bookings
            throw new AccessDeniedException("You are not authorized to access these bookings");
        }

        // 4. Return bookings for the authorized user
        return bookingRepository.findByUserUserId(userId);
    }

}

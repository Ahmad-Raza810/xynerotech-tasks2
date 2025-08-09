package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.bookingDTO.CreateBookingDTO;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;


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



    @Override
    public Booking addBooking(CreateBookingDTO bookingDTO) {

        //checking user exists or not.
       AppUser user=userRepository
                .findById(bookingDTO.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("user with id '"+bookingDTO.getUserId()+"' not exists."));

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
                bookingDTO.getUserId(),
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
}

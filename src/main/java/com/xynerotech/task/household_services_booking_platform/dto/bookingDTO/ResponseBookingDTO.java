package com.xynerotech.task.household_services_booking_platform.dto.bookingDTO;

import com.xynerotech.task.household_services_booking_platform.entities.Booking;
import com.xynerotech.task.household_services_booking_platform.entities.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBookingDTO {

    private Long bookingId;

    private Long userId;

    private Long serviceId;

    private LocalDate bookingDate;

    private BookingStatus status;


    public static ResponseBookingDTO bookingToDto(Booking booking){
        ResponseBookingDTO bookingDTO=new ResponseBookingDTO();
        bookingDTO.setBookingId(booking.getId());
        bookingDTO.setUserId(booking.getUser().getUserId());
        bookingDTO.setServiceId(booking.getService().getId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setStatus(booking.getStatus());

        return bookingDTO;
    }
}

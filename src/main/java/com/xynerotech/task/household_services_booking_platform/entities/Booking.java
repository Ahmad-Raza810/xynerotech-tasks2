package com.xynerotech.task.household_services_booking_platform.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private HomeService service;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;  // PENDING, CONFIRMED, CANCELLED

}

package com.xynerotech.task.household_services_booking_platform.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xynerotech.task.household_services_booking_platform.validation.ValidLocalTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


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

    @NotNull(message = "Time is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @ValidLocalTime
    private LocalTime time;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;

}

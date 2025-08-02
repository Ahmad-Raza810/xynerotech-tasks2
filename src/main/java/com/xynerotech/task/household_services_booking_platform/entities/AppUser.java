package com.xynerotech.task.household_services_booking_platform.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userId;

    @NotBlank(message = "username is required.")
    private String userName;


    @NotBlank(message = "email is required.")
    @Email(message = "email should be valid.")
    private String email;

    @NotBlank(message = "password is required.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    //ignore it  is next week task
//    @OneToMany(mappedBy = "user")
//    private List<Booking> bookings;


}

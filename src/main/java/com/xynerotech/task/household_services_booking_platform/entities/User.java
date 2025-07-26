package com.xynerotech.task.household_services_booking_platform.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

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

    private String role;


}

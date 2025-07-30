package com.xynerotech.task.household_services_booking_platform.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private Role role;


}

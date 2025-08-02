package com.xynerotech.task.household_services_booking_platform.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String email;
    private String role;
}

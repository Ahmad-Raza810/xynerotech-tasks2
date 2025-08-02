package com.xynerotech.task.household_services_booking_platform.dto;

import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    @NotBlank(message = "Username is required")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;


    public static AppUser dtoToUser(RegisterUserDTO dto){
        AppUser user=new AppUser();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

}

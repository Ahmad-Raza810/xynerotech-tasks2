package com.xynerotech.task.household_services_booking_platform.dto;

import com.xynerotech.task.household_services_booking_platform.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;


    public static User dtoToUser(CreateUserDTO createUserDTO){
        User user=new User();
        user.setUserName(createUserDTO.getUserName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        return user;
    }

    public static CreateUserDTO userToDto(User user){
        CreateUserDTO createUserDTO=new CreateUserDTO();
        createUserDTO.setUserName(user.getUserName());
        createUserDTO.setEmail(user.getEmail());
        createUserDTO.setPassword(user.getPassword());
        return createUserDTO;
    }
}

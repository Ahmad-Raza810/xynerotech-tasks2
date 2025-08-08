package com.xynerotech.task.household_services_booking_platform.dto.appuserDTO;

import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;


    public static AppUser dtoToUser(CreateUserDTO createUserDTO){
        AppUser user=new AppUser();
        user.setUserName(createUserDTO.getUserName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    public static CreateUserDTO userToDto(AppUser user){
        CreateUserDTO createUserDTO=new CreateUserDTO();
        createUserDTO.setUserName(user.getUserName());
        createUserDTO.setEmail(user.getEmail());
        createUserDTO.setPassword(user.getPassword());
        return createUserDTO;
    }
}

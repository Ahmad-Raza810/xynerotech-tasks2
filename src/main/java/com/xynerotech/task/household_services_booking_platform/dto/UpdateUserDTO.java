package com.xynerotech.task.household_services_booking_platform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.validation.AtLeastOneFieldNotEmpty;
import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneFieldNotEmpty(message = "At least one field (userName, email, or password) must be provided.")
public class UserUpdateDTO {

    private String userName;
    private Long userId;
    private String role;

    @Email(message = "Invalid email format.")
    private String email;

    @JsonIgnore
    private String password;

    public static AppUser dtoToUser(UserUpdateDTO userUpdateDTO) {
        AppUser user = new AppUser();

        if (userUpdateDTO.getUserName() != null)
            user.setUserName(userUpdateDTO.getUserName());

        if (userUpdateDTO.getEmail() != null)
            user.setEmail(userUpdateDTO.getEmail());

        if (userUpdateDTO.getPassword() != null)
            user.setPassword(userUpdateDTO.getPassword());

        return user;
    }

    public static UserUpdateDTO userToDto(AppUser user) {
        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }
}

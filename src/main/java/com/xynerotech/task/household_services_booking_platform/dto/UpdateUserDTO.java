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
public class UpdateUserDTO {

    private String userName;
    private Long userId;
    private String role;

    @Email(message = "Invalid email format.")
    private String email;

    @JsonIgnore
    private String password;

    public static AppUser dtoToUser(UpdateUserDTO updateUserDTO) {
        AppUser user = new AppUser();

        if (updateUserDTO.getUserName() != null)
            user.setUserName(updateUserDTO.getUserName());

        if (updateUserDTO.getEmail() != null)
            user.setEmail(updateUserDTO.getEmail());

        if (updateUserDTO.getPassword() != null)
            user.setPassword(updateUserDTO.getPassword());

        return user;
    }

    public static UpdateUserDTO userToDto(AppUser user) {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }
}

package com.xynerotech.task.household_services_booking_platform.dto;

import com.xynerotech.task.household_services_booking_platform.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String userName;
    private String email;
    private String role;


    public static UserResponseDTO userToResponseDto(User user){
        UserResponseDTO responseDTO=new UserResponseDTO();
        responseDTO.setUserId(user.getUserId());
        responseDTO.setUserName(user.getUserName());
        responseDTO.setEmail(user.getEmail());

        if (user.getRole() != null) {
            responseDTO.setRole(user.getRole().name());
        } else {
            responseDTO.setRole(null);  // ya "UNKNOWN" if you prefer
        }

        return responseDTO;
    }
}

    package com.xynerotech.task.household_services_booking_platform.dto;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.xynerotech.task.household_services_booking_platform.entities.User;
    import com.xynerotech.task.household_services_booking_platform.validation.AtLeastOneFieldNotEmpty;
    import jakarta.validation.constraints.Email;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
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

        public static User dtoToUser(UserUpdateDTO userUpdateDTO){
            User user=new User();
            user.setUserName(userUpdateDTO.getUserName());
            user.setEmail(userUpdateDTO.getEmail());
            user.setPassword(userUpdateDTO.getPassword());
            return user;
        }

        public static UserUpdateDTO userToDto(User user){
            UserUpdateDTO userUpdateDTO=new UserUpdateDTO();
            userUpdateDTO.setUserId(user.getUserId());
            userUpdateDTO.setUserName(user.getUserName());
            userUpdateDTO.setEmail(user.getEmail());
            userUpdateDTO.setPassword(user.getPassword());
            userUpdateDTO.setRole(user.getRole().name());
            return userUpdateDTO;
        }
    }



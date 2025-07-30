package com.xynerotech.task.household_services_booking_platform.controller;

import com.xynerotech.task.household_services_booking_platform.dto.CreateUserDTO;
import com.xynerotech.task.household_services_booking_platform.dto.UserResponseDTO;
import com.xynerotech.task.household_services_booking_platform.dto.UserUpdateDTO;
import com.xynerotech.task.household_services_booking_platform.entities.User;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //api endpoint for adding new user.
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<UserResponseDTO>> addUser(@Valid @RequestBody CreateUserDTO createUserDTO, HttpServletRequest request) {
        User returnedUser = userService.addUser(CreateUserDTO.dtoToUser(createUserDTO));
        UserResponseDTO responseDTO=UserResponseDTO.userToResponseDto(returnedUser);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                "User added successfully.",
                LocalDateTime.now(),
                responseDTO,
                true,
                HttpStatus.CREATED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //api endpoint for getting  user by id.
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
       UserResponseDTO responseDTO=UserResponseDTO.userToResponseDto(user);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                "User fetched successfully",
                LocalDateTime.now(),
                responseDTO,
                true,
                HttpStatus.OK.value()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    //api endpoint for getting all users.
    @GetMapping("/get")
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
        List<User> users= userService.getAllUser();
        List<UserResponseDTO> responseDTOList=users
                .stream()
                .map(UserResponseDTO::userToResponseDto)
                .toList();
        return  new ResponseEntity<>(responseDTOList,HttpStatus.OK);
    }


    //api endpoint for updating a user.
    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<UserUpdateDTO>> updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody UserUpdateDTO updatedUser){
        User returnedUser=userService.updateUser(userId,UserUpdateDTO.dtoToUser(updatedUser));
        UserUpdateDTO returnedUserUpdateDTO=UserUpdateDTO.userToDto(returnedUser);
        ApiResponse<UserUpdateDTO> response=new ApiResponse<>(
                "user successfully updated.",
                LocalDateTime.now(),
                returnedUserUpdateDTO,
                true,
                HttpStatus.OK.value()
        );
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    //api endpoint for deleting a user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        ApiResponse<User> response=new ApiResponse<>(
                "user deleted successfully.",
                LocalDateTime.now(),
                null,
                true,
                HttpStatus.OK.value()
        );
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    


}

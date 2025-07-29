package com.xynerotech.task.household_services_booking_platform.controller;

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
    public ResponseEntity<ApiResponse<User>> addUser(@Valid @RequestBody User user, HttpServletRequest request) {
        System.out.println("Incoming path: " + request.getRequestURI()); // Debug log
        User returnedUser = userService.addUser(user);

        ApiResponse<User> response = new ApiResponse<>(
                "User added successfully.",
                LocalDateTime.now(),
                returnedUser,
                true,
                HttpStatus.CREATED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
       // return ResponseEntity.ok("Test response - no redirect");

    }

    //api endpoint for getting  user by id.
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        ApiResponse<User> response = new ApiResponse<>(
                "User fetched successfully",
                LocalDateTime.now(),
                user,
                true,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
    }




    //api endpoint for getting all users.
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users= userService.getAllUser();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }


    //api endpoint for updating a user.
    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody User updatedUser){
        User returnedUser=userService.updateUser(userId,updatedUser);
        ApiResponse<User> response=new ApiResponse<>(
                "user successfully updated.",
                LocalDateTime.now(),
                returnedUser,
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

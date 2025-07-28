package com.xynerotech.task.household_services_booking_platform.controller;

import com.xynerotech.task.household_services_booking_platform.entities.User;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //api endpoint for adding new user.
    @GetMapping("/add")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody User user){
        User returnedUser=userService.addUser(user);
        ApiResponse<User> response=new ApiResponse<>(
                "User found.",
                LocalDateTime.now(),
                returnedUser,
                true,
                HttpStatus.CREATED.value()
        );

       return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //api endpoint for getting all users.
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users= userService.getAllUser();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }


    //api endpoint for updating a user.
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable("userId") Long userId, @RequestBody User updatedUser){
        User returnedUser=userService.updateUser(userId,updatedUser);
        ApiResponse<User> response=new ApiResponse<>(
                "user successfully updated.",
                LocalDateTime.now(),
                returnedUser,
                true,
                HttpStatus.OK.value()
        );
        return  new ResponseEntity<>(HttpStatus.OK);
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

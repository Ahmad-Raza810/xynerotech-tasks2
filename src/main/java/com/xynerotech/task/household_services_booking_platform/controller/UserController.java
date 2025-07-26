package com.xynerotech.task.household_services_booking_platform.controller;

import com.xynerotech.task.household_services_booking_platform.entities.User;
import com.xynerotech.task.household_services_booking_platform.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<User> addUser(@RequestBody User user){
        User returnedUser=userService.addUser(user);
       return  new ResponseEntity<>(returnedUser, HttpStatus.CREATED);
    }
}

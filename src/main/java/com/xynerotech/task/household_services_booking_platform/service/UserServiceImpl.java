package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    //constructor injection.
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //service method for add a new user.
    @Override
    public AppUser addUser(AppUser user) {
        return userRepository.save(user);
    }

    //service method for get  user by id.
    @Override
    public AppUser getUserById(Long userId) {
      return   userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("User with id '"+userId+"' not exist. "));
    }

    //service method for getting all user.
    @Override
    public List<AppUser> getAllUser() {
        return userRepository.findAll();
    }


    //service method for deleting a user.
    @Override
    public void deleteUser(Long userId) {
        AppUser returnedUser=userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("User with id '"+userId+"' not exist. "));

       userRepository.delete(returnedUser);
    }


    //service method for updating a user.
    @Override
    public AppUser updateUser(Long userId, AppUser updatedUser) {
        AppUser returnedUser=userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("User with id '"+userId+"' not exist. "));


        if (StringUtils.hasText(updatedUser.getUserName()))
              returnedUser.setUserName(updatedUser.getUserName());

        if(StringUtils.hasText(updatedUser.getEmail()))
            returnedUser.setEmail(updatedUser.getEmail());

        if (StringUtils.hasText(updatedUser.getPassword()))
            returnedUser.setPassword(updatedUser.getPassword());


        return userRepository.save(returnedUser);
    }
}

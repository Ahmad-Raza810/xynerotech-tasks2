package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.entities.User;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.response.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User addUser(User user) {
        return userRepository.save(user);
    }

    //service method for get  user by id.
    @Override
    public User getUserById(Long userId) {
      return   userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("User with id '"+userId+"' not exist. "));
    }

    //service method for getting all user.
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    //service method for deleting a user.
    @Override
    public void deleteUser(Long userId) {
       User returnedUser=userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("User with id '"+userId+"' not exist. "));

       userRepository.delete(returnedUser);
    }


    //service method for updating a user.
    @Override
    public User updateUser(Long userId, User updatedUser) {
        User returnedUser=userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("User with id '"+userId+"' not exist. "));


        if (updatedUser.getUserName()!=null)
              returnedUser.setUserName(updatedUser.getUserName());

        if(updatedUser.getEmail()!=null)
            returnedUser.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword()!=null)
            returnedUser.setPassword(updatedUser.getPassword());


        return userRepository.save(returnedUser);
    }
}

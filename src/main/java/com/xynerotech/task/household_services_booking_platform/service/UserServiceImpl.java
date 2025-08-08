package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.dto.LoginRequestDTO;
import com.xynerotech.task.household_services_booking_platform.dto.LoginResponseDTO;
import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.entities.Role;
import com.xynerotech.task.household_services_booking_platform.exception.DuplicateResourceException;
import com.xynerotech.task.household_services_booking_platform.exception.InvalidCredentialsException;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.repository.UserRepository;
import com.xynerotech.task.household_services_booking_platform.security.JwtUtil;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService{



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    private UserRepository userRepository;

    //constructor injection.
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //service method for add a new user.
    @Override
    public AppUser addUser(AppUser user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already registered.");
        }
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



    //service method for registering a user.
    public AppUser registerUser(AppUser user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);

        return userRepository.save(user);
    }


    //service method for login a user.
    public LoginResponseDTO loginUser(LoginRequestDTO dto) {
        AppUser user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        System.out.println("Generated token: " + token);


        return new LoginResponseDTO(token, user.getEmail(), user.getRole().name());
    }



}

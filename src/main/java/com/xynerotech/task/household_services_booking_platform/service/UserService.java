package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.entities.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User getUserById(Long id);

    List<User> getAllUser();

    void deleteUser(Long id);

    User updateUser(Long id,User user);
}

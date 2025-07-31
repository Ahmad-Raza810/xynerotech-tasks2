package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import java.util.List;

public interface UserService {

    AppUser addUser(AppUser user);

    AppUser getUserById(Long id);

    List<AppUser> getAllUser();

    void deleteUser(Long id);

    AppUser updateUser(Long id,AppUser user);
}

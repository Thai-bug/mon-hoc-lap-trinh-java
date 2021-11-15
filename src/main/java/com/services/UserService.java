package com.services;

import com.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User login(String phoneNumber, String password);

    boolean addUser(User user);

    List<User> getUsers(String phoneNumber);
}

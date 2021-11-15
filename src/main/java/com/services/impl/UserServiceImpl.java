package com.services.impl;

import com.pojos.User;
import com.repositories.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String phoneNumber, String password) {
        List<User> users = userRepository.getUserByPhone(phoneNumber);
        if (users.size() == 0) {
            return null;
        }
        User userIn = users.get(0);
        if(userIn.getPassword().equals(password)){
            return userIn;
        }
        return null;
    }
}

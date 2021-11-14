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
    public User login(String username, String password) {
        List<User> users = userRepository.getUserByPhone(username);
        if (users.size() == 0) {
            return null;
        }
        User user = users.get(0);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}

package com.services;

import com.pojos.User;

public interface UserService {
    User login(String username, String password);
}

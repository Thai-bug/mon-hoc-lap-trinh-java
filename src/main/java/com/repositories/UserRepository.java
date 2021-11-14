package com.repositories;

import com.pojos.User;

import java.util.List;

public interface UserRepository {
    List<User> getUserByPhone(String phone);
}

package com.services.impl;

import com.pojos.User;
import com.repositories.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
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

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public List<User> getUsers(String phoneNumber) {
        return userRepository.getUserByPhone(phoneNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        List<User> users = getUsers(phoneNumber);
        if(users.isEmpty())
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        User user = users.get(0);
        System.out.println(user.getPassword());

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), auth);
    }
}

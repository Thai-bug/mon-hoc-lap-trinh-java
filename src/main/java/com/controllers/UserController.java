package com.controllers;

import com.pojos.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute(value = "user" ) @Valid User user,
            BindingResult result) {
        System.out.println(user.getPhoneNumber());
        if (result.hasErrors()) {
            return "login";
        }
//        User userIn = userService.login(user.getPhoneNumber(), user.getPassword());
//        if (userIn == null) {
//            return "forward:/login";
//        }

        return "login";
    }
}

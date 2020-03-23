package com.english.controller;

import com.english.entity.User;
import com.english.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private UserService userService;
    private Logger log = LoggerFactory.getLogger(WordController.class);

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        log.info("registration page");
        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String addUser(@RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String password) {
        User user = userService.getByUsername(username);
        if (user != null) {
            log.info("user with username: {} already exists!", username);
            return "registration";
        }
        log.info("create new user with username: {}", username);
        userService.save(username, email, password);
        return "redirect:/";
    }
}

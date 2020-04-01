package com.english.controller.security;

import com.english.controller.WordController;
import com.english.entity.User;
import com.english.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);
    private static final String REGISTRATION = "registration";

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        LOGGER.info("registration page");
        return REGISTRATION;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String addUser(@RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String password) {
        User user = userService.getByUsername(username);
        if (user != null) {
            LOGGER.info("User with username: {} already exists!", username);
            return REGISTRATION;
        }
        LOGGER.info("Create a new user with username: {}", username);
        userService.save(username, email, password);
        return "redirect:/";
    }
}

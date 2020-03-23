package com.english.util.security;

import com.english.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private UserService userService;

    @Autowired
    SecurityUtil(UserService userService) {
        this.userService = userService;
    }

    public Integer getUserId() {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userService.getUserId(username);
    }
}

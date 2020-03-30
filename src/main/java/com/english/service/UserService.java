package com.english.service;

import com.english.dao.UserDao;
import com.english.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public Integer getUserId(String username) {
        return userDao.getUserId(username);
    }

    public void save(String username, String email, String password) {
        userDao.save(username, email, password);
    }

    public Integer getUserId() {
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserId(username);
    }
}


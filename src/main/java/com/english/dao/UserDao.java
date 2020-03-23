package com.english.dao;

import com.english.entity.User;

public interface UserDao {
    User getByUsername(String username);

    User getByEmail(String email);

    void save(String username, String email, String password);

    Integer getUserId(String username);
}

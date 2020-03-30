package com.english.dao.impl;

import com.english.dao.UserDao;
import com.english.entity.User;
import com.english.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String username, String email, String password) {
        String sql = "INSERT INTO user(username, email, password) VALUES(?,?,?)";
        jdbcTemplate.update(sql, username, email, password);
    }

    public User getByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer getUserId(String username) {
        User user = getByUsername(username);
        if (user != null) {
            return user.getId();
        } else return -1;
    }
}

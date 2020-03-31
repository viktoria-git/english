package com.english.dao.impl;

import com.english.dao.LevelDao;
import com.english.entity.Level;
import com.english.util.mapper.LevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDaoImpl implements LevelDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LevelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Level> getAll() {
        String sql = "SELECT * FROM level";
        return jdbcTemplate.query(sql, new LevelMapper());
    }

    public Level get(String level) {
        String sql = "SELECT * FROM level WHERE level_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{level}, new LevelMapper());
    }

    @Override
    public Level get(Integer id) {
        String sql = "SELECT * FROM level WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new LevelMapper());
    }
}

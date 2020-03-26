package com.english.dao.impl;

import com.english.dao.TopicDao;
import com.english.entity.Topic;
import com.english.util.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TopicDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Topic> getAll() {
        String sql = "SELECT * FROM topic";
        return jdbcTemplate.query(sql, new TopicMapper());
    }

    public Topic getById(Integer id) {
        String sql = "SELECT * FROM topic WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TopicMapper());
    }

    @Override
    public Topic getByName(String topic){

        String sql = "SELECT * FROM topic WHERE topic_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{topic}, new TopicMapper());
    }
}

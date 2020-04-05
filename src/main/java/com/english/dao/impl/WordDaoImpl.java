package com.english.dao.impl;

import com.english.dao.WordDao;
import com.english.dao.builder.QueryBuilder;
import com.english.entity.Word;
import com.english.util.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class WordDaoImpl implements WordDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(String word, String translate, Integer userId, Integer topicId, Integer levelId) {

        String sql = "INSERT INTO word(word,translate,user_id,topic_id,level_id) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, word, translate, userId, topicId, levelId);
    }

    @Override
    public List<Word> getAll(Integer userId) {
        String sql = "SELECT * FROM word where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new WordMapper());
    }

    @Override
    public Word get(Integer userId, String word) {
        String sql = "SELECT * FROM word WHERE user_id = ? and word = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId, word}, new WordMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void remove(Integer userId, Integer id) {
        String sql = "DELETE FROM word WHERE user_id = ? and id = ?";
        jdbcTemplate.update(sql, userId, id);
    }

    public void removeAll(Integer userId) {
        String sql = "DELETE FROM word WHERE id > 0";
        jdbcTemplate.update(sql);
    }

    public List<Word> filter(Integer userId, Integer topicId, Integer levelId) {
        QueryBuilder query = filterSql(userId, topicId, levelId).build();
        return jdbcTemplate.query(query.getSql(), query.getParameters(), new WordMapper());
    }

    @Override
    public List<Word> sort(Integer userId, String sort, Integer topicId, Integer levelId) {
        QueryBuilder.Builder builder = filterSql(userId, topicId, levelId);
        builder.orderBy(sort);
        QueryBuilder query = builder.build();
        return jdbcTemplate.query(query.getSql(), query.getParameters(), new WordMapper());
    }

    private QueryBuilder.Builder filterSql(Integer userId, Integer topicId, Integer levelId) {
        QueryBuilder.Builder builder = new QueryBuilder.Builder().addSql("SELECT * FROM word WHERE user_id = ?");
        builder.addParameter(userId);
        if (levelId != 0) {
            builder.and()
                    .addSql("level_id=?")
                    .addParameter(levelId);
        }
        if (topicId != 0) {
            builder.and()
                    .addSql("topic_id=?")
                    .addParameter(topicId);
        }
        return builder;
    }
}
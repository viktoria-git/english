package com.english.dao.impl;

import com.english.dao.WordDao;
import com.english.dao.builder.QueryBuilder;
import com.english.entity.Word;
import com.english.util.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void create(String word, String translate, Integer topicId, Integer levelId) {
        String sql = "INSERT INTO word(word,translate,topic_id,level_id) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, word, translate, topicId, levelId);
    }

    @Override
    public List<Word> getAll() {
        String sql = "SELECT * FROM word";
        return jdbcTemplate.query(sql, new WordMapper());
    }

    @Override
    public Word get(String word) {
        String sql = "SELECT * FROM word WHERE word = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{word}, new WordMapper());
    }

    @Override
    public Word getById(Integer id) {
        String sql = "SELECT * FROM word WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WordMapper());
    }

    @Override
    public void remove(Integer id) {
        String sql = "DELETE FROM word WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM word WHERE id > 0";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Word> filter(Integer topicId, Integer levelId) {
        QueryBuilder.Builder builder = new QueryBuilder.Builder().addSql("SELECT * FROM word");
        if(levelId != 0){
            builder.where()
                    .addSql("level_id=?")
                    .addParameter(levelId);
        }
        if(topicId != 0){
            builder.and()
                    .addSql("topic_id=?")
                    .addParameter(topicId);
        }

        QueryBuilder query = builder.build();
        return jdbcTemplate.query(query.getSql(), query.getParameters(), new WordMapper());
    }

    public List<Word> sort(String sort) {
        String sql = "SELECT * FROM word order by " + sort;
        return jdbcTemplate.query(sql, new WordMapper());
    }
}

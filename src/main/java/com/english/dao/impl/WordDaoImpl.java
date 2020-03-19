package com.english.dao;

import com.english.dao.WordDao;
import com.english.entity.Word;
import com.english.util.mapper.WordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WordDaoImpl implements WordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public WordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(String word, String translate, Integer topicId) {
        String sql = "INSERT INTO word(word,translate,topic_id) VALUES(?,?,?)";
        jdbcTemplate.update(sql,word,translate,topicId);
    }

    @Override
    public List<Word> getAll() {
        String sql = "SELECT * FROM word";
        return jdbcTemplate.query(sql,new WordMapper());
    }

    @Override
    public Word get(String word) {
        String sql = "SELECT * FROM word WHERE word=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{word},new WordMapper());
    }

    @Override
    public Word getById(Integer id) {
        String sql = "SELECT * FROM word WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new WordMapper());
    }

    @Override
    public void remove(Integer id) {
        String sql = "DELETE FROM word WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }


    public void removeAll() {
        String sql = "DELETE FROM word WHERE id > 0";
        jdbcTemplate.update(sql);
    }

}

package com.english.dao.impl;

import com.english.dao.WordDao;
import com.english.entity.Word;
import com.english.util.Utils;
import com.english.util.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class WordDaoImpl implements WordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public WordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(String word, String translate, String color, Integer userId) {
        String sql = "INSERT INTO word(word,translate,color,user_id) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, word, translate, color, userId);
    }

    @Override
    public List<Word> getAll(Integer userId) {
        String sql = "SELECT * FROM word where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new WordMapper());
    }

    @Override
    public Word get(Integer userId, String word) {
        String sql = "SELECT * FROM word WHERE user_id = ? and word=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, word}, new WordMapper());
    }

    @Override
    public Word getById(Integer id) {
        String sql = "SELECT * FROM word WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WordMapper());
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

    public List<Word> sortByWord(Integer userId) {
        List<Word> words = getAll(userId);
        return Utils.sort(Comparator.comparing(Word::getWord), words);
    }


    public List<Word> sortByTranslate(Integer userId) {
        List<Word> words = getAll(userId);
        return Utils.sort(Comparator.comparing(Word::getTranslate), words);
    }

}

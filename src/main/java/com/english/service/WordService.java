package com.english.service;

import com.english.entity.Word;
import com.english.dao.WordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    WordDao jdbcTemplateWordDao;

    @Autowired
    public WordService(WordDao jdbcTemplateWordDao) {
        this.jdbcTemplateWordDao = jdbcTemplateWordDao;
    }

    public void create(String word, String translate, String color) {
        jdbcTemplateWordDao.create(word, translate, color);
    }

    public List<Word> getAll() {
        return jdbcTemplateWordDao.getAll();
    }

    public Word get(String word) {
        return jdbcTemplateWordDao.get(word);
    }

    public Word getById(Integer id) {
        return jdbcTemplateWordDao.getById(id);
    }

    public void remove(Integer id) {
        jdbcTemplateWordDao.remove(id);
    }
}

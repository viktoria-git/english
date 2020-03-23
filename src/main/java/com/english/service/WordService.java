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

    public void create(String word, String translate, String color,Integer userId) {
        jdbcTemplateWordDao.create(word, translate, color,userId);
    }

    public List<Word> getAll(Integer userId) {
        return jdbcTemplateWordDao.getAll(userId);
    }

    public Word get(Integer userId,String word) {
        return jdbcTemplateWordDao.get(userId,word);
    }

    public Word getById(Integer id) {
        return jdbcTemplateWordDao.getById(id);
    }

    public void remove(Integer userId,Integer id) {
        jdbcTemplateWordDao.remove(userId,id);
    }

    public void removeAll(Integer userId){
        jdbcTemplateWordDao.removeAll(userId);
    }

    public List<Word> sortByWord(Integer userId) {
        return jdbcTemplateWordDao.sortByWord(userId);
    }

    public List<Word> sortByTranslate(Integer userId) {
        return jdbcTemplateWordDao.sortByTranslate(userId);
    }

}

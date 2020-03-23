package com.english.dao;

import com.english.entity.Word;

import java.util.List;

public interface WordDao {

    void create(String word, String translate, String color,Integer userId);

    List<Word> getAll(Integer userId);

    Word get(Integer userId,String word);

    Word getById(Integer id);

    void remove(Integer userId,Integer id);

    void removeAll(Integer userId);

    List<Word> sortByWord(Integer userId);

    List<Word> sortByTranslate(Integer userId);
}

package com.english.dao;

import com.english.entity.Word;

import java.util.List;

public interface WordDao {

    void create(String word, String translate, Integer id);

    List<Word> getAll();

    Word get(String word);

    Word getById(Integer id);

    void remove(Integer id);

    void removeAll();

}
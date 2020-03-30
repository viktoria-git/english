package com.english.dao;

import com.english.entity.Word;

import java.util.List;

public interface WordDao {

    void create(String word, String translate, Integer userId, Integer topicId, Integer levelId);

    List<Word> getAll(Integer userId);

    Word get(Integer userId, String word);

    void remove(Integer userId, Integer id);

    void removeAll(Integer userId);

    List<Word> filter(Integer userId, Integer topicId, Integer levelId);

    List<Word> sort(Integer userId, String sort);
}

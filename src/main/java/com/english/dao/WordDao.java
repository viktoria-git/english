package com.english.dao;

import com.english.entity.Word;

import java.util.List;

public interface WordDao {

    void create(String word, String translate, Integer topicId, Integer levelId);

    List<Word> getAll(Integer userId);

    Word get(Integer userId,String word);

    Word getById(Integer id);

    void remove(Integer userId,Integer id);

    void removeAll(Integer userId);

    List<Word> sortByWord(Integer userId);
    List<Word> filter(Integer topicId, Integer levelId);

    List<Word> sortByTranslate(Integer userId);
    List<Word> sort(String sort);

}

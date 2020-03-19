package com.english.service;

import com.english.dao.TopicDao;
import com.english.entity.Word;
import com.english.dao.WordDao;
import com.english.entity.WordTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    WordDao wordDao;
    TopicDao topicDao;

    @Autowired
    public WordService(WordDao jdbcTemplateWordDao, TopicDao topicDao) {
        this.wordDao = jdbcTemplateWordDao;
        this.topicDao = topicDao;
    }

    public void create(String word, String translate, Integer id) {
        wordDao.create(word, translate, id);
    }

    public List<WordTo> getAll() {
        List<Word> words = wordDao.getAll();
        return words.stream()
                .map(word -> new WordTo(word,topicDao.get(word.getTopicId())))
                .collect(Collectors.toList());
    }


    public Word get(String word) {
        return wordDao.get(word);
    }

    public Word getById(Integer id) {
        return wordDao.getById(id);
    }

    public void remove(Integer id) {
        wordDao.remove(id);
    }

    public void removeAll(){
        wordDao.removeAll();
    }

    public List<WordTo> sortByWord() {
        return sort(Comparator.comparing(WordTo::getWord));
    }

    public List<WordTo> sortByTranslate() {
        return sort(Comparator.comparing(WordTo::getTranslate));
    }

    public List<WordTo> sortByTopic() {
        return sort(Comparator.comparing(WordTo::getTopicName));
    }

    private List<WordTo> sort(Comparator<WordTo> comparator){
        return getAll().stream().sorted(comparator).collect(Collectors.toList());
    }


    private Iterator<WordTo> insertAsFirst(Word word) {
        List<WordTo> words = getAll();
        WordTo wordTo = new WordTo(word, topicDao.get(word.getTopicId()));
        words.remove(wordTo);

        wordTo.setAllocated(true);
        words.add(0, wordTo);

        return words.iterator();
    }
}

package com.english.service;

import com.english.entity.Level;
import com.english.entity.Topic;
import com.english.entity.Word;
import com.english.dao.WordDao;
import com.english.entity.WordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final WordDao wordDao;
    private final TopicService topicService;
    private final LevelService levelService;

    @Autowired
    public WordService(WordDao wordDao, TopicService topicService, LevelService levelService) {
        this.wordDao = wordDao;
        this.topicService = topicService;
        this.levelService = levelService;
    }

    public void create(String word, String translate, String topic, String level) {
        Integer topicId = topicService.getByName(topic).getId();
        Integer levelId = levelService.getByName(level).getId();
        wordDao.create(word, translate, topicId, levelId);
    }

    public List<WordResponse> getAllWordResponses() {
        return createWordResponseListFromWordList(wordDao.getAll());
    }

    public List<WordResponse> filter(String topic, String level) {
        Integer topicId = 0;
        if (!topic.equals("0")) {
            topicId = topicService.getByName(topic).getId();
        }
        Integer levelId = 0;
        if (!level.equals("0")) {
            levelId = levelService.getByName(level).getId();
        }
        List<Word> filteredWords = wordDao.filter(topicId, levelId);
        return createWordResponseListFromWordList(filteredWords);
    }

    public List<WordResponse> findAndInsertAsFirst(String searchedWord) {
        WordResponse wordResponse = get(searchedWord);
        List<WordResponse> wordResponses = getAllWordResponses();
        Collections.swap(wordResponses, 0, wordResponses.indexOf(wordResponse));
        wordResponses.get(0).setAllocated(true);
        return wordResponses;
    }

    public WordResponse get(String word) {
        Word w = wordDao.get(word);
        return createWordResponseFromWord(w);
    }

    public void remove(Integer id) {
        wordDao.remove(id);
    }

    public void removeAll() {
        wordDao.removeAll();
    }

    public List<WordResponse> sort(String sort) {
        List<Word> sortedWords = wordDao.sort(sort);
        return createWordResponseListFromWordList(sortedWords);
    }

    private WordResponse createWordResponseFromWord(Word word) {
        Topic topic = topicService.getById(word.getTopicId());
        Level level = levelService.getById(word.getLevelId());
        return new WordResponse(word, topic, level);
    }

    private List<WordResponse> createWordResponseListFromWordList(List<Word> words) {
        return words.stream()
                .map(this::createWordResponseFromWord)
                .collect(Collectors.toList());
    }
}

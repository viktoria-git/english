package com.english.service;

import com.english.entity.Level;
import com.english.entity.Topic;
import com.english.entity.Word;
import com.english.dao.WordDao;
import com.english.entity.WordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private WordDao wordDao;
    private TopicService topicService;
    private LevelService levelService;

    @Autowired
    public WordService(WordDao wordDao, TopicService topicService, LevelService levelService) {
        this.wordDao = wordDao;
        this.topicService = topicService;
        this.levelService = levelService;
    }

    public void create(String word, String translate, Integer topicId, Integer levelId) {
        wordDao.create(word, translate, topicId, levelId);
    }

    public List<WordResponse> getAllResponses() {
        return createWordResponseListFromWordList(wordDao.getAll());
    }

    public List<WordResponse> filter(Integer topicId, Integer levelId) {
        List<Word> filteredWords = wordDao.filter(topicId, levelId);
        return createWordResponseListFromWordList(filteredWords);
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

    public void removeAll() {
        wordDao.removeAll();
    }

    public List<WordResponse> sort(String sort) {
        List<Word> sortedWords = wordDao.sort(sort);
        return createWordResponseListFromWordList(sortedWords);
    }

    public List<WordResponse> insertAsFirst(Word word) {
        List<WordResponse> wordResponses = getAllResponses();
        WordResponse wordResponse = createWordResponseFromWord(word);
        wordResponses.remove(wordResponse);

        wordResponse.setAllocated(true);
        wordResponses.add(0, wordResponse);

        return wordResponses;
    }

    private WordResponse createWordResponseFromWord(Word word) {
        Topic topic = topicService.get(word.getTopicId());
        Level level = levelService.get(word.getLevelId());
        return new WordResponse(word, topic, level);
    }

    private List<WordResponse> createWordResponseListFromWordList(List<Word> words) {
        return words.stream()
                .map(this::createWordResponseFromWord)
                .collect(Collectors.toList());
    }
}

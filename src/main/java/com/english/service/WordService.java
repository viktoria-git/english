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

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer DEFAULT_TOPIC = 10;

    private final WordDao wordDao;
    private final TopicService topicService;
    private final LevelService levelService;

    @Autowired
    public WordService(WordDao wordDao, TopicService topicService, LevelService levelService) {
        this.wordDao = wordDao;
        this.topicService = topicService;
        this.levelService = levelService;
    }

    public void create(Integer userId, String word, String translate, String topic, String level) {
        Integer topicId = topic.equals("0") ? DEFAULT_TOPIC : topicService.get(topic).getId();
        Integer levelId = level.equals("0") ? DEFAULT_LEVEL : levelService.get(level).getId();
        if (wordDao.get(userId, word) == null) {
            wordDao.create(word, translate, userId, topicId, levelId);
        }
    }

    public List<WordResponse> getAllWordResponses(Integer userId) {
        return createWordResponseListFromWordList(wordDao.getAll(userId));
    }

    public List<WordResponse> filter(Integer userId, String topic, String level) {
        Integer topicId = topic.equals("0") ? 0 : topicService.get(topic).getId();
        Integer levelId = level.equals("0") ? 0 : levelService.get(level).getId();

        List<Word> filteredWords = wordDao.filter(userId, topicId, levelId);
        return createWordResponseListFromWordList(filteredWords);
    }

    public Word get(Integer userId, String word) {
        return wordDao.get(userId, word);
    }

    public void remove(Integer userId, Integer id) {
        wordDao.remove(userId, id);
    }

    public void removeAll(Integer userId) {
        wordDao.removeAll(userId);
    }

    public List<WordResponse> sort(Integer userId, String sort) {
        List<Word> sortedWords = wordDao.sort(userId, sort);
        return createWordResponseListFromWordList(sortedWords);
    }

    public List<WordResponse> find(Integer userId, String searchedWord) {
        Word word = get(userId, searchedWord);
        if (word != null) {
            WordResponse wordResponse = createWordResponseFromWord(word);
            List<WordResponse> wordResponses = getAllWordResponses(userId);
            Collections.swap(wordResponses, 0, wordResponses.indexOf(wordResponse));
            wordResponses.get(0).setAllocated(true);
            return wordResponses;

        } else return getAllWordResponses(userId);
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

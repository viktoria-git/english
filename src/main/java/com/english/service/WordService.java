package com.english.service;

import com.english.entity.Level;
import com.english.entity.Topic;
import com.english.entity.Word;
import com.english.dao.WordDao;
import com.english.entity.WordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private static final String URL = "http://localhost:3000/translate/";
    private final RestTemplate template = new RestTemplate();

    private final WordDao wordDao;
    private final TopicService topicService;
    private final LevelService levelService;
    private final UserService userService;

    private String level = "0";
    private String topic = "0";

    @Autowired
    public WordService(WordDao wordDao, TopicService topicService, LevelService levelService, UserService userService) {
        this.wordDao = wordDao;
        this.topicService = topicService;
        this.levelService = levelService;
        this.userService = userService;
    }

    public void create(String word, String topic, String level) {
        Integer userId = userService.getUserId();
        if (get(word) == null) {
            Integer topicId = topic.equals("0") ? 10 : topicService.getId(topic);
            Integer levelId = level.equals("0") ? 1 : levelService.getId(level);
            String translate = template.getForObject(URL + word, String.class);
            wordDao.create(word, translate, userId, topicId, levelId);
        }
    }

    public void create(String word, String translate, String topic, String level) {
        Integer userId = userService.getUserId();
        if (get(word) == null) {
            Integer topicId = topic.equals("0") ? 10 : topicService.getId(topic);
            Integer levelId = level.equals("0") ? 1 : levelService.getId(level);
            wordDao.create(word, translate, userId, topicId, levelId);
        }
    }

    public List<WordResponse> getAllWordResponses() {
        this.level = "0";
        this.topic = "0";
        Integer userId = userService.getUserId();
        return createWordResponseListFromWordList(wordDao.getAll(userId));
    }

    public List<WordResponse> filter(String topic, String level) {
        Integer userId = userService.getUserId();
        Integer topicId = topic.equals("0") ? 0 : topicService.getId(topic);
        Integer levelId = level.equals("0") ? 0 : levelService.getId(level);
        this.level = level;
        this.topic = topic;
        List<Word> filteredWords = wordDao.filter(userId, topicId, levelId);
        return createWordResponseListFromWordList(filteredWords);
    }

    public void remove(Integer id) {
        Integer userId = userService.getUserId();
        wordDao.remove(userId, id);
    }

    public void removeAll() {
        Integer userId = userService.getUserId();
        wordDao.removeAll(userId);
    }

    public List<WordResponse> sort(String sort) {
        Integer userId = userService.getUserId();
        Integer topicId = topic.equals("0") ? 0 : topicService.getId(topic);
        Integer levelId = level.equals("0") ? 0 : levelService.getId(level);
        List<Word> sortedWords = wordDao.sort(userId, sort, topicId, levelId);
        return createWordResponseListFromWordList(sortedWords);
    }

    public List<WordResponse> find(String searchedWord) {
        Integer userId = userService.getUserId();
        Word word = wordDao.get(userId, searchedWord);
        if (word != null) {
            WordResponse wordResponse = createWordResponseFromWord(word);
            List<WordResponse> wordResponses = getAllWordResponses();
            Collections.swap(wordResponses, 0, wordResponses.indexOf(wordResponse));
            wordResponses.get(0).setAllocated(true);
            return wordResponses;
        } else return getAllWordResponses();
    }

    private Word get(String word) {
        int userId = userService.getUserId();
        return wordDao.get(userId, word);
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

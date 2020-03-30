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

    private final WordDao wordDao;
    private final TopicService topicService;
    private final LevelService levelService;
    private final UserService userService;

    @Autowired
    public WordService(WordDao wordDao, TopicService topicService, LevelService levelService, UserService userService) {
        this.wordDao = wordDao;
        this.topicService = topicService;
        this.levelService = levelService;
        this.userService = userService;
    }

    public void create(String word, String translate, String topic, String level) {
        int userId = userService.getUserId();
        int topicId = topic.equals("0") ? 10 : topicService.get(topic).getId();
        int levelId = level.equals("0") ? 1 : levelService.get(level).getId();
        if (!wordDao.contains(userId, word)) {
            wordDao.create(word, translate, userId, topicId, levelId);
        }
    }

    public List<WordResponse> getAllWordResponses() {
        int userId = userService.getUserId();
        return createWordResponseListFromWordList(wordDao.getAll(userId));
    }

    public List<WordResponse> filter(String topic, String level) {
        int userId = userService.getUserId();
        int topicId = topic.equals("0") ? 0 : topicService.get(topic).getId();
        int levelId = level.equals("0") ? 0 : levelService.get(level).getId();

        List<Word> filteredWords = wordDao.filter(userId, topicId, levelId);
        return createWordResponseListFromWordList(filteredWords);
    }

    public Word get(String word) {
        int userId = userService.getUserId();
        if (wordDao.contains(userId, word)) {
            return wordDao.get(userId, word);
        } else return null;
    }

    public void remove(Integer id) {
        int userId = userService.getUserId();
        wordDao.remove(userId, id);
    }

    public void removeAll() {
        int userId = userService.getUserId();
        wordDao.removeAll(userId);
    }

    public List<WordResponse> sort(String sort) {
        int userId = userService.getUserId();
        List<Word> sortedWords = wordDao.sort(userId, sort);
        return createWordResponseListFromWordList(sortedWords);
    }

    public List<WordResponse> find(String word) {
        int userId = userService.getUserId();
        if (wordDao.contains(userId, word)) {
            Word searchedWord = get(word);
            return insertAsFirst(searchedWord);
        } else return null;
    }

    public List<WordResponse> insertAsFirst(Word word) {
        List<WordResponse> wordResponses = getAllWordResponses();
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

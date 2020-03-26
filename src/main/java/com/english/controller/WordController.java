package com.english.controller;

import com.english.entity.WordResponse;
import com.english.service.LevelService;
import com.english.service.TopicService;
import com.english.service.WordService;
import com.english.util.Utils;
import com.english.util.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.*;

@Controller
@Validated
public class WordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);

    private static final String INDEX_PAGE = "index";
    private static final String REDIRECT = "redirect:/vocabulary";
    private Logger log = LoggerFactory.getLogger(WordController.class);

    private final WordService wordService;
    private final TopicService topicService;
    private final LevelService levelService;
    private final SecurityUtil util;

    @Autowired
    public WordController(WordService service, TopicService topicService,
                          LevelService levelService, SecurityUtil util) {
        this.wordService = service;
        this.topicService = topicService;
        this.levelService = levelService;
        this.util = util;
    }

    @RequestMapping(path = "/vocabulary", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        log.info("getAll words");
        int userId = util.getUserId();
        List<WordResponse> wordResponses = Utils.transferTo(wordService.getAll(userId));
        return updateListOfWordResponses(wordResponses, model);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestParam @NotEmpty String word,
                      @RequestParam @NotEmpty String translate,
                      @RequestParam String topic, @RequestParam String level) {
        LOGGER.info("Create a new word: {}, translate: {} with topic = {} and level = {}",
                word, translate, topic, level);
        int userId = util.getUserId();
        wordService.create(word, translate, topic, level, userId);
        return REDIRECT;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        LOGGER.info("Get all words");
        List<WordResponse> wordResponses = wordService.getAllWordResponses();
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/sort", method = RequestMethod.GET)
    public String sort(Map<String, Object> model, @RequestParam String sort) {
        LOGGER.info("Get all words");
        List<WordResponse> wordResponses = wordService.sort(sort);
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public String filter(Map<String, Object> model,
                         @RequestParam String topic,
                         @RequestParam String level) {
        LOGGER.info("Get all filtered words");
        List<WordResponse> wordResponses = wordService.filter(topic, level);
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        LOGGER.info("Get a word = {}", searchedWord);
        List<WordResponse> wordResponses = wordService.findAndInsertAsFirst(searchedWord);
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        LOGGER.info("Remove a word with id = {} ", id);
        int userId = util.getUserId();
        wordService.remove(userId, id);
        return REDIRECT;
    }

    @RequestMapping(path = "/removeAll", method = RequestMethod.GET)
    public String removeAll(Integer userId) {
        LOGGER.info("Remove all words");
        wordService.removeAll(userId);
        return REDIRECT;
    }

    //generic Controller action
    private String updateListOfWordResponses(List<WordResponse> words, Map<String, Object> model) {
        model.put("words", words);
        return INDEX_PAGE;
    }
}

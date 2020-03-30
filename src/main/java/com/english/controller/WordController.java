package com.english.controller;

import com.english.entity.WordResponse;
import com.english.service.LevelService;
import com.english.service.TopicService;
import com.english.service.WordService;
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

    private static final String INDEX_PAGE = "index";
    private static final String REDIRECT = "redirect:/vocabulary";
    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);

    private final WordService wordService;
    private final TopicService topicService;
    private final LevelService levelService;

    @Autowired
    public WordController(WordService service, TopicService topicService, LevelService levelService) {
        this.wordService = service;
        this.topicService = topicService;
        this.levelService = levelService;
    }

    @RequestMapping(path = "/vocabulary", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        LOGGER.info("Get all words");
        List<WordResponse> wordResponses = wordService.getAllWordResponses();
        return updateListOfWordResponses(wordResponses, model);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestParam @NotEmpty String word,
                      @RequestParam @NotEmpty String translate,
                      @RequestParam String topic,
                      @RequestParam String level) {
        LOGGER.info("Create word = {} with topic = {} and level = {}", word, topic, level);
        wordService.create(word, translate, topic, level);
        return REDIRECT;
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        LOGGER.info("Remove word with id = {}", id);
        wordService.remove(id);
        return REDIRECT;
    }

    @RequestMapping(path = "/removeAll", method = RequestMethod.GET)
    public String removeAll() {
        LOGGER.info("Remove all words");
        wordService.removeAll();
        return REDIRECT;
    }

    @RequestMapping(path = "/sort", method = RequestMethod.GET)
    public String sort(Map<String, Object> model, @RequestParam String sort) {
        LOGGER.info("Get all sorted words");
        List<WordResponse> wordResponses = wordService.sort(sort);
        return updateListOfWordResponses(wordResponses, model);
    }

    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public String filter(Map<String, Object> model,
                         @RequestParam String topic,
                         @RequestParam String level) {
        LOGGER.info("Get all filtered words");
        List<WordResponse> wordResponses = wordService.filter(topic, level);
        return updateListOfWordResponses(wordResponses, model);
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String word, Map<String, Object> model) {
        List<WordResponse> wordResponses = wordService.find(word);
        if (wordResponses == null) {
            LOGGER.info("Vocabulary doesn`t exists word = {}", word);
            return getAll(model);
        }
        LOGGER.info("Get word {}", word);
        return updateListOfWordResponses(wordResponses, model);
    }

    //generic Controller action
    private String updateListOfWordResponses(List<WordResponse> words, Map<String, Object> model) {
        model.put("topics", topicService.getAll());
        model.put("levels", levelService.getAll());
        model.put("words", words);
        return INDEX_PAGE;
    }
}

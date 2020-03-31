package com.english.controller;

import com.english.entity.WordResponse;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);
    private final WordService wordService;

    @Autowired
    public WordController(WordService service) {
        this.wordService = service;
    }

    @ResponseBody
    @RequestMapping(path = "/vocabulary", method = RequestMethod.GET)
    public List<WordResponse> getAll(@RequestParam Integer userId) {
        LOGGER.info("Get all words for user with id = {}", userId);
        return wordService.getAllWordResponses(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add(@RequestParam Integer userId,
                    @RequestParam @NotEmpty String word,
                    @RequestParam @NotEmpty String translate,
                    @RequestParam String topic, @RequestParam String level) {
        LOGGER.info("Create word = {} with topic = {} and level = {} for user with id = {}", word, topic, level, userId);
        wordService.create(userId, word, translate, topic, level);
    }

    @ResponseBody
    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public void remove(@RequestParam Integer userId, @RequestParam Integer id) {
        LOGGER.info("Remove word ith id = {} for user with id = {}", id, userId);
        wordService.remove(userId, id);
    }

    @ResponseBody
    @RequestMapping(path = "/removeAll", method = RequestMethod.DELETE)
    public void removeAll(@RequestParam Integer userId) {
        LOGGER.info("Remove all words for user with = {}", userId);
        wordService.removeAll(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/sort", method = RequestMethod.GET)
    public List<WordResponse> sort(@RequestParam Integer userId, @RequestParam String sort) {
        LOGGER.info("Get all sorted words for user with id = {}", userId);
        return wordService.sort(userId, sort);
    }

    @ResponseBody
    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public List<WordResponse> filter(@RequestParam Integer userId, @RequestParam String topic, @RequestParam String level) {
        LOGGER.info("Get all filtered words for user with id = {}", userId);
        return wordService.filter(userId, topic, level);
    }

    @ResponseBody
    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public List<WordResponse> find(@RequestParam Integer userId, @RequestParam String searchedWord) {
        List<WordResponse> wordResponses = wordService.find(userId, searchedWord);
        if (wordResponses == null) {
            LOGGER.info("Vocabulary doesn`t exists word = {}", searchedWord);
            return getAll(userId);
        }
        LOGGER.info("Get word {} for user with id = {}", searchedWord, userId);
        return wordResponses;
    }
}
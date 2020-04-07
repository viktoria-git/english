package com.english.controller;

import com.english.entity.WordResponse;
import com.english.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.*;

@RestController
@Validated
public class WordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);
    private final WordService wordService;

    @Autowired
    public WordController(WordService service) {
        this.wordService = service;
    }

    @GetMapping(path = "/vocabulary")
    public List<WordResponse> getAll(@RequestParam Integer userId) {
        LOGGER.info("Get all words for user with id = {}", userId);
        return wordService.getAllWordResponses(userId);
    }

    @PostMapping(path = "/add")
    public void add(@RequestParam Integer userId,
                    @RequestParam @NotEmpty String word,
                    @RequestParam @NotEmpty String translate,
                    @RequestParam String topic, @RequestParam String level) {
        LOGGER.info("Create word = {} with topic = {} and level = {} for user with id = {}", word, topic, level, userId);
        wordService.create(userId, word, translate, topic, level);
    }

    @DeleteMapping(path = "/remove")
    public void remove(@RequestParam Integer userId, @RequestParam Integer id) {
        LOGGER.info("Remove word ith id = {} for user with id = {}", id, userId);
        wordService.remove(userId, id);
    }

    @DeleteMapping(path = "/removeAll")
    public void removeAll(@RequestParam Integer userId) {
        LOGGER.info("Remove all words for user with = {}", userId);
        wordService.removeAll(userId);
    }

    @GetMapping(path = "/order")
    public List<WordResponse> order(@RequestParam Integer userId, @RequestParam String order) {
        LOGGER.info("Get all sorted words for user with id = {}", userId);
        return wordService.order(userId, order);
    }

    @GetMapping(path = "/filter")
    public List<WordResponse> filter(@RequestParam Integer userId,
                                     @RequestParam String topic,
                                     @RequestParam String level) {
        LOGGER.info("Get all filtered words for user with id = {}", userId);
        return wordService.filter(userId, topic, level);
    }

    @GetMapping(path = "/find")
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
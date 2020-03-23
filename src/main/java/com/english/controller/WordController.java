package com.english.controller;

import com.english.entity.Color;
import com.english.entity.Word;
import com.english.entity.WordResponse;
import com.english.service.WordService;
import com.english.util.Utils;
import com.english.util.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;


@Controller
@Validated
public class WordController {
    private static final String INDEX_PAGE = "index";
    private static final String REDIRECT = "redirect:/vocabulary";
    private Logger log = LoggerFactory.getLogger(WordController.class);

    private WordService wordService;
    private SecurityUtil util;

    @Autowired
    public WordController(WordService service,SecurityUtil util) {
        this.wordService = service;
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
    public String add(@RequestParam @NotNull String word,
                      @RequestParam @NotNull String translate) {
        log.info("create word {}", word);
        int userId = util.getUserId();
        wordService.create(word, translate, Color.valueOf().getFieldName(),userId);
        return REDIRECT;
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        log.info("remove word with {} id", id);
        int userId = util.getUserId();
        wordService.remove(userId, id);
        return REDIRECT;
    }

    @RequestMapping(path = "/removeAll", method = RequestMethod.GET)
    public String removeAll(Integer userId) {
        log.info("remove all words");
        wordService.removeAll(userId);
        return REDIRECT;
    }

    @RequestMapping(path = "/sortByWord", method = RequestMethod.GET)
    public String sortByWord(Map<String, Object> model) {
        int userId = util.getUserId();
        List<WordResponse> words = Utils.transferTo(wordService.sortByWord(userId));
        return updateListOfWordResponses(words, model);
    }

    @RequestMapping(path = "/sortByTranslate", method = RequestMethod.GET)
    public String sortByTranslate(Map<String, Object> model) {
        int userId = util.getUserId();
        List<WordResponse> words = Utils.transferTo(wordService.sortByTranslate(userId));
        return updateListOfWordResponses(words, model);
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        int userId = util.getUserId();
        Word w = wordService.get(userId,searchedWord);
        List<Word> words = wordService.getAll(userId);
        model.put("words", Utils.insertAsFirst(w,words));
        return INDEX_PAGE;
    }

    //generic Controller action
    private String updateListOfWordResponses(List<WordResponse> words, Map<String, Object> model) {
        model.put("words", words);
        return INDEX_PAGE;
    }


}

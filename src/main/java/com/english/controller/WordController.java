package com.english.controller;

import com.english.dao.impl.WordDaoImpl;
import com.english.entity.Word;
import com.english.entity.WordTo;
import com.english.service.TopicService;
import com.english.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class WordController {
    private static final String INDEX_PAGE = "index";
    private static final String REDIRECT = "redirect:/";
    private Logger log = LoggerFactory.getLogger(WordDaoImpl.class);

    WordService wordService;
    TopicService topicService;

    @Autowired
    WordController(WordService service, TopicService topicService) {
        this.wordService = service;
        this.topicService = topicService;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestParam String word, @RequestParam String translate, @RequestParam Integer id) {
        log.info("create word {}",word);
        wordService.create(word, translate, id);
        return REDIRECT;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        log.info("getAll words");
        List<WordTo> words = wordService.getAll();
        return refreshIndex(words, model);
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        Word word = wordService.get(searchedWord);
        log.info("get word {}",word);
        model.put("words", insertAsFirst(word));
        return INDEX_PAGE;
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        log.info("remove word with {} id", id);
        wordService.remove(id);
        return REDIRECT;
    }

    @RequestMapping(path = "/removeAll", method = RequestMethod.GET)
    public String removeAll() {
        log.info("remove all words");
        wordService.removeAll();
        return REDIRECT;
    }

    @RequestMapping(path = "/sortByWord", method = RequestMethod.GET)
    public String sortByWord(Map<String, Object> model) {
        log.info("sort words by original word");
        List<WordTo> words = wordService.sortByWord();
        return refreshIndex(words, model);
    }

    @RequestMapping(path = "/sortByTranslate", method = RequestMethod.GET)
    public String sortByTranslate(Map<String, Object> model) {
        log.info("sort words by translate");
        List<WordTo> words = wordService.sortByTranslate();
        return refreshIndex(words, model);
    }

    @RequestMapping(path = "/sortByTopic", method = RequestMethod.GET)
    public String sortByTopic(Map<String, Object> model) {
        log.info("sort words by topic");
        List<WordTo> words = wordService.sortByTopic();
        return refreshIndex(words, model);
    }

    //generic Controller action
    private String refreshIndex(List<WordTo> words, Map<String, Object> model) {
        model.put("topics", topicService.getAll());
        model.put("words", words);
        return INDEX_PAGE;
    }

}

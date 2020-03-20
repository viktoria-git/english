package com.english.controller;

import com.english.entity.Word;
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

import javax.validation.constraints.NotNull;
import java.util.*;

@Controller
@Validated
public class WordController {
    private static final String INDEX_PAGE = "index";
    private static final String ERROR_PAGE = "error_page";
    private static final String REDIRECT = "redirect:/";
    private Logger log = LoggerFactory.getLogger(WordController.class);

    private WordService wordService;
    private TopicService topicService;
    private LevelService levelService;

    @Autowired
    public WordController(WordService service, TopicService topicService,
                          LevelService levelService) {
        this.wordService = service;
        this.topicService = topicService;
        this.levelService = levelService;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestParam @NotNull String word,
                      @RequestParam @NotNull String translate,
                      @RequestParam Integer topicId, @RequestParam Integer levelId) {
        log.info("create word {}", word);
        wordService.create(word, translate, topicId, levelId);
        return REDIRECT;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        log.info("getAll words");
        List<WordResponse> wordResponses = wordService.getAllResponses();
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/sort", method = RequestMethod.GET)
    public String sort(Map<String, Object> model, @RequestParam String sort) {
        log.info("getAll words");
        List<WordResponse> wordResponses = wordService.sort(sort);
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public String filter(Map<String, Object> model,
                         @RequestParam Integer topicId,
                         @RequestParam Integer levelId) {
        log.info("getAll filtered words");
        List<WordResponse> wordResponses = wordService.filter(topicId,levelId);
        return refreshIndex(wordResponses, model);
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        Word word = wordService.get(searchedWord);
        log.info("get word {}", word);
        List<WordResponse> wordResponses = wordService.insertAsFirst(word);
        return refreshIndex(wordResponses,model);
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

    //generic Controller action
    private String refreshIndex(List<WordResponse> words, Map<String, Object> model) {
        model.put("topics", topicService.getAll());
        model.put("levels", levelService.getAll());
        model.put("words", words);
        return INDEX_PAGE;
    }

    private String getErrorPage(Map<String, Object> model, String error) {
        model.put("error", error);
        return ERROR_PAGE;
    }

}

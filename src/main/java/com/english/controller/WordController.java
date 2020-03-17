package com.english.controller;

import com.english.entity.Color;
import com.english.entity.Word;
import com.english.entity.WordTo;
import com.english.service.WordService;
import com.english.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class WordController {
    private static final String INDEX_PAGE = "index";
    private static final String REDIRECT ="redirect:/";

    WordService service;

    @Autowired
    WordController(WordService service) {
        this.service = service;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestParam String word, @RequestParam String translate) {
        service.create(word, translate, Color.valueOf().getFieldName());
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model){
        List<Word> words = service.getAll();
        return refreshIndex(words, model);
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        Word word = service.get(searchedWord);
        model.put("words", insertAsFirst(word));
        return INDEX_PAGE;
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        service.remove(id);
        return REDIRECT;
    }

    @RequestMapping(path = "/removeAll", method = RequestMethod.GET)
    public String removeAll() {
        service.removeAll();
        return REDIRECT;
    }

    @RequestMapping(path = "/sortByWord", method = RequestMethod.GET)
    public String sortByWord(Map<String, Object> model) {
        List<Word> words = service.sortByWord();
        return refreshIndex(words, model);
    }

    @RequestMapping(path = "/sortByTranslate", method = RequestMethod.GET)
    public String sortByTranslate(Map<String, Object> model) {
        List<Word> words = service.sortByTranslate();
       return refreshIndex(words, model);
    }

    private String refreshIndex(List<Word>words,Map<String, Object> model){
        List<WordTo> wordTos = Utils.transferTo(words);
        model.put("words", wordTos);
        return INDEX_PAGE;
    }

    private Iterator<WordTo> insertAsFirst(Word word) {
        List<WordTo> words = Utils.transferTo(service.getAll());
        WordTo wordTo = new WordTo(word);
        words.remove(wordTo);

        wordTo.setAllocated(true);
        words.add(0,wordTo);

        return words.iterator();
    }
}

package com.english.controller;

import com.english.entity.Word;
import com.english.entity.WordTo;
import com.english.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class MainController {
    WordService service;

    @Autowired
    MainController(WordService service) {
        this.service = service;
    }


    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addNewWord(@RequestParam String word, @RequestParam String translate) {
        Word newWord = new Word();
        service.create(word,translate,newWord.getColor());
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAllWords(Map<String, Object> model) {
        model.put("words",transferTo());
        return "index";
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        Word word = findWord(searchedWord);
        model.put("words", transfer(word));
        return "index";
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        service.remove(id);
        return "redirect:/";
    }

    private Iterator<WordTo> transfer(Word word) {
        List<Word> words = service.getAll();
        return words.stream()
                .map(w->new WordTo(w, word.equals(w)))
                .collect(Collectors.toList()).iterator();
    }

    private List<WordTo> transferTo() {
        List<Word> words = service.getAll();
        return words.stream()
                .map(word -> new WordTo(word,false))
                .collect(Collectors.toList());
    }

    private Word findWord(String searchedWord) {
        return service.get(searchedWord);
    }


}

package com.english.controller;

import com.english.entity.Word;
import com.english.entity.WordTo;
import com.english.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
public class MainController {
    @Autowired
    MainController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    private WordRepository wordRepository;

    @RequestMapping(path = "/add", method = RequestMethod.POST) // Map ONLY POST Requests
    public String addNewWord(@RequestParam String word, @RequestParam String translate) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Word newWord = new Word();
        newWord.setWord(word);
        newWord.setTranslate(translate);
        wordRepository.save(newWord);
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAllWords(Map<String, Object> model) {
        model.put("words",transferTo());
        // This returns a JSON or XML with the users
        return "index";
        //return ResponseEntity.ok(wordRepository.findAll());
    }

    @RequestMapping(path = "/find", method = RequestMethod.GET) // Map ONLY POST Requests
    public String find(@RequestParam String searchedWord, Map<String, Object> model) {
        Word word = findWord(searchedWord);
        model.put("words", transfer(word));
        return "index";
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        wordRepository.deleteById(id);
        return "redirect:/";
    }

    private Iterator<WordTo> transfer(Word word) {
        return StreamSupport.stream(wordRepository.findAll().spliterator(),false)
                .map(w->new WordTo(w, word.equals(w)))
                .collect(Collectors.toList()).iterator();
    }

    private Iterator<WordTo> transferTo() {
        return StreamSupport.stream(wordRepository.findAll().spliterator(),false)
                .map(word -> new WordTo(word,false))
                .collect(Collectors.toList()).iterator();
    }

    private Word findWord(String searchedWord) {
        return StreamSupport.stream(wordRepository.findAll().spliterator(),false)
                .filter(word -> word.getWord().equals(searchedWord)).findFirst().orElse(null);
    }
}

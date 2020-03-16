package com.english.controller;

import com.english.entity.Word;
import com.english.entity.WordTo;
import com.english.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller // This means that this class is a Controller
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
        if (word != null) {
            model.put("words", transfer(findWord(searchedWord)));
            //model.put("words", Collections.singletonList(word));
            return "index";
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Integer id) {
        wordRepository.deleteById(id);
        return "redirect:/";
    }

    private Iterator<WordTo> transfer(Word word) {
        List<WordTo> wordTos = new ArrayList<>();
        for (Word w : wordRepository.findAll()) {
            if (word.equals(w)) {
                wordTos.add(new WordTo(w, true));
            } else {
                wordTos.add(new WordTo(w, false));
            }
        }
        return wordTos.iterator();
    }

    private Iterator<WordTo> transferTo() {
        List<WordTo> wordTos = new ArrayList<>();
        for (Word w : wordRepository.findAll()) {
            wordTos.add(new WordTo(w, false));
        }
        return wordTos.iterator();
    }

    private Word findWord(String searchedWord) {
        for (Word word : wordRepository.findAll()) {
            if (word.getWord().equals(searchedWord)) {
                return word;
            }
        }
        return null;
    }
}

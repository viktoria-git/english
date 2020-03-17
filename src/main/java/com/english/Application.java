package com.english;

import com.english.entity.Color;
import com.english.entity.Word;
import com.english.service.WordService;
import com.english.util.FileUtils;
import com.english.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {
    @Autowired
    private WordService wordService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @PostConstruct
    private void init() throws IOException {
        wordService.removeAll();
        Map<String, String> allWords = FileUtils.getAllWords();
        List<Word> words = Utils.getListOfWords(allWords);
        words.forEach(word -> wordService
                .create(word.getWord(), word.getTranslate(), Color.valueOf().getFieldName()));
    }
}

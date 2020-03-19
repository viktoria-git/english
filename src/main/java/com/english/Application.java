package com.english;

import com.english.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    private WordService wordService;
    public static Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        log.info("HELLO");
        SpringApplication.run(Application.class,args);
    }

//    @PostConstruct
//    private void init() throws IOException {
//        wordService.removeAll();
//        Map<String, String> allWords = FileUtils.getAllWords();
//        List<Word> words = Utils.getListOfWords(allWords);
//        words.forEach(word -> wordService
//                .create(word.getWord(), word.getTranslate(), Color.valueOf().getFieldName()));
//    }
}

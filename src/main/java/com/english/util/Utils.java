package com.english.util;

import com.english.entity.Color;
import com.english.entity.Word;
import com.english.entity.WordTo;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static List<Word> getListOfWords(Map<String, String> allWords) {
        List<Word> words = new ArrayList<>();
        for (Map.Entry<String, String> pair : allWords.entrySet()) {
            Word word = new Word();
            word.setWord(pair.getKey());
            word.setTranslate(pair.getValue());
            word.setColor(Color.valueOf().getFieldName());
            words.add(word);
        }
        return words;
    }

    public static List<WordTo> transferTo(List<Word> list) {
        return list.stream()
                .map(WordTo::new)
                .collect(Collectors.toList());
    }

    public static Iterator<WordTo> insertAsFirst(Word word, List<Word>words) {
        List<WordTo> wordTos = Utils.transferTo(words);
        WordTo wordTo = new WordTo(word);
        wordTos.remove(wordTo);

        wordTo.setAllocated(true);
        wordTos.add(0,wordTo);

        return wordTos.iterator();
    }

    public static List<Word> sort(Comparator<Word> comparator, List<Word>words){
        return words.stream().sorted(comparator).collect(Collectors.toList());
    }
}

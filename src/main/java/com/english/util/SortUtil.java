package com.english.util;

import com.english.entity.WordResponse;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortUtil {
    public static List<WordResponse> sortByWord(List<WordResponse> wordResponses) {
        return sort(wordResponses, WordResponse::getWord);
    }

    public static List<WordResponse> sortByTranslate(List<WordResponse> wordResponses) {
        return sort(wordResponses, WordResponse::getTranslate);
    }

    public static List<WordResponse> sortByTopic(List<WordResponse> wordResponses) {
        return sort(wordResponses, WordResponse::getTopicName);
    }

    public static List<WordResponse> sortByLevel(List<WordResponse> wordResponses) {
        return wordResponses.stream()
                .sorted(Comparator.comparing(WordResponse::getLevelId))
                .collect(Collectors.toList());
    }

    public static List<WordResponse> dispatchSort(List<WordResponse> wordResponses, String sort) {
        switch (sort) {
            case "word":
                return sortByWord(wordResponses);
            case "translate":
                return sortByTranslate(wordResponses);
            case "topic":
                return sortByTopic(wordResponses);
            case "level":
                return sortByLevel(wordResponses);
        }
        return wordResponses;
    }

    private static List<WordResponse> sort(List<WordResponse> wordResponses, Function<WordResponse, String> func) {
        return wordResponses.stream()
                .sorted(Comparator.comparing(func))
                .collect(Collectors.toList());
    }
}

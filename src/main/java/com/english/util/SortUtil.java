package com.english.util;

import com.english.entity.WordResponse;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortUtil {

    public static List<WordResponse> dispatchSort(List<WordResponse> wordResponses, String sort) {
        switch (sort) {
            case "word":
                return sort(wordResponses, WordResponse::getWord);
            case "translate":
                return sort(wordResponses, WordResponse::getTranslate);
            case "topic":
                return sort(wordResponses, WordResponse::getTopicName);
            case "level":
                return sortByLevel(wordResponses, WordResponse::getId);
        }
        return wordResponses;
    }

    private static List<WordResponse> sort(List<WordResponse> wordResponses, Function<WordResponse, String> func) {
        return wordResponses.stream()
                .sorted(Comparator.comparing(func))
                .collect(Collectors.toList());
    }

    private static List<WordResponse> sortByLevel(List<WordResponse> wordResponses, Function<WordResponse,Integer> func) {
        return wordResponses.stream()
                .sorted(Comparator.comparing(func))
                .collect(Collectors.toList());
    }
}

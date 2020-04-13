package com.english.utils;

import com.english.entity.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static final String EXPECTED_TRANSLATE_CLEAR = "сброс / убирать";
    public static final String EXPECTED_TRANSLATE_RAIN = "дождь / литься";

    public static final List<Level> levelList = new ArrayList<>();
    public static final List<Topic> topicList = new ArrayList<>();

    public static List<WordResponse> allWordResponses = new ArrayList<>();

    public static List<WordResponse> allWordResponsesAddedFromFileWithoutTranslate = new ArrayList<>();
    public static List<WordResponse> allWordResponsesAddedFromFileWithTranslate = new ArrayList<>();

    public static List<WordResponse> allSortedWordResponsesByWord = new ArrayList<>();
    public static List<WordResponse> allSortedWordResponsesByLevel = new ArrayList<>();

    public static List<WordResponse> allFilteredWordResponsesByLevel = new ArrayList<>();
    public static List<WordResponse> allFilteredWordResponsesByTopic = new ArrayList<>();
    public static List<WordResponse> allFilteredWordResponsesByTopicAndLevel = new ArrayList<>();

    public static List<WordResponse> allWordResponsesWithSearchedWord = new ArrayList<>();

    static {
        topicList.add(new Topic(1, "Travel", "travel"));
        topicList.add(new Topic(2, "Other", "other"));
        topicList.add(new Topic(3, "Work", "work"));

        levelList.add(new Level(1, "Elementary"));
        levelList.add(new Level(2, "Pre-Intermediate"));
        levelList.add(new Level(3, "Intermediate"));
    }

    static {
        allWordResponses.add(new WordResponse(1, "journey", "путешествие", "travel", false, "Travel", "Elementary"));
        allWordResponses.add(new WordResponse(2, "flight", "полет", "travel", false, "Travel", "Elementary"));
        allWordResponses.add(new WordResponse(3, "ticket", "билет", "other", false, "Other", "Elementary"));
        allWordResponses.add(new WordResponse(4, "cat", "кот", "travel", false, "Travel", "Pre-Intermediate"));
        allWordResponses.add(new WordResponse(5, "declare", "объявить", "other", false, "Other", "Intermediate"));
        allWordResponses.add(new WordResponse(125, "dog", "собака", "travel", false, "Travel", "Pre-Intermediate"));
    }

    static {
        allWordResponsesWithSearchedWord.add(new WordResponse(4, "cat", "кот", "travel", true, "Travel", "Pre-Intermediate"));
        allWordResponsesWithSearchedWord.add(allWordResponses.get(1));
        allWordResponsesWithSearchedWord.add(allWordResponses.get(2));
        allWordResponsesWithSearchedWord.add(allWordResponses.get(0));
        allWordResponsesWithSearchedWord.add(allWordResponses.get(4));
        allWordResponsesWithSearchedWord.add(allWordResponses.get(5));
    }

    static {
        allSortedWordResponsesByWord.addAll(allWordResponses.stream()
                .sorted((Comparator.comparing(WordResponse::getWord)))
                .collect(Collectors.toList()));
    }

    static {
        allSortedWordResponsesByLevel.add(allWordResponses.get(0));
        allSortedWordResponsesByLevel.add(allWordResponses.get(1));
        allSortedWordResponsesByLevel.add(allWordResponses.get(2));
        allSortedWordResponsesByLevel.add(allWordResponses.get(3));
        allSortedWordResponsesByLevel.add(allWordResponses.get(5));
        allSortedWordResponsesByLevel.add(allWordResponses.get(4));
    }

    static {
        allFilteredWordResponsesByLevel.addAll(allWordResponses.stream()
                .filter(wordResponse -> wordResponse.getLevel().equals("Pre-Intermediate"))
                .collect(Collectors.toList()));
    }

    static {
        allFilteredWordResponsesByTopic.addAll(allWordResponses.stream()
                .filter(wordResponse -> wordResponse.getTopic().equals("Other"))
                .collect(Collectors.toList()));
    }

    static {
        allFilteredWordResponsesByTopicAndLevel.addAll(allWordResponses.stream()
                .filter((wordResponse -> wordResponse.getTopic().equals("Travel")))
                .filter(wordResponse -> wordResponse.getLevel().equals("Elementary"))
                .collect(Collectors.toList()));
    }

    static {
        allWordResponsesAddedFromFileWithoutTranslate.addAll(allWordResponses);
        allWordResponsesAddedFromFileWithoutTranslate.add(new WordResponse(126, "rain", "дождь / литься", "other", false, "Other", "Elementary"));
    }

    static {
        allWordResponsesAddedFromFileWithTranslate.addAll(allWordResponses);
        allWordResponsesAddedFromFileWithTranslate.add(new WordResponse(126, "winter", "зима", "other", false, "Other", "Elementary"));
    }
}

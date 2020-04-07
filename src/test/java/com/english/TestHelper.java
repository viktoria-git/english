package com.english;

import com.english.entity.*;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {
    public static final Word WORD_ONE = new Word();
    public static final Word WORD_TWO = new Word();
    public static final Word WORD_FOR_SEARCH = new Word();

    public static final WordResponse WORD_RESPONSE_ONE;
    public static final WordResponse WORD_RESPONSE_TWO;
    public static final WordResponse WORD_RESPONSE_FOR_SEARCH;

    public static final Level LEVEL = new Level();
    public static final Topic TOPIC = new Topic();

    public static final List<Word> words = new ArrayList<>();
    public static final List<WordResponse> wordResponses = new ArrayList<>();
    public static final List<WordResponse> wordResponsesWithFoundWord = new ArrayList<>();

    public static final List<Level> levelList = new ArrayList<>();
    public static final List<Topic> topicList = new ArrayList<>();

    static {
        WORD_ONE.setWord("dog");
        WORD_ONE.setTopicId(1);
        WORD_ONE.setLevelId(1);

        WORD_TWO.setWord("cat");
        WORD_TWO.setTopicId(1);
        WORD_TWO.setLevelId(1);

        WORD_FOR_SEARCH.setWord("cat");
        WORD_FOR_SEARCH.setTopicId(1);
        WORD_FOR_SEARCH.setLevelId(1);

        words.add(WORD_ONE);
        words.add(WORD_TWO);
    }

    static {
        LEVEL.setId(1);
        LEVEL.setLevelName("Elementary");
        levelList.add(LEVEL);

        TOPIC.setId(1);
        TOPIC.setTopicName("Travel");
        TOPIC.setColor("travel");

        topicList.add(TOPIC);
    }

    static {
        WORD_RESPONSE_ONE = new WordResponse(WORD_ONE, TOPIC, LEVEL);
        WORD_RESPONSE_TWO = new WordResponse(WORD_TWO, TOPIC, LEVEL);
        WORD_RESPONSE_FOR_SEARCH = new WordResponse(WORD_FOR_SEARCH, TOPIC, LEVEL);

        wordResponses.add(WORD_RESPONSE_ONE);
        wordResponses.add(WORD_RESPONSE_TWO);

        WORD_RESPONSE_FOR_SEARCH.setAllocated(true);
        wordResponsesWithFoundWord.add(WORD_RESPONSE_FOR_SEARCH);
        wordResponsesWithFoundWord.add(WORD_RESPONSE_ONE);
    }
}

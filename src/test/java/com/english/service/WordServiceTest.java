package com.english.service;

import com.english.TestHelper;
import com.english.dao.WordDao;
import com.english.entity.Word;
import com.english.entity.WordResponse;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.english.TestHelper.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class WordServiceTest {

    private WordDao wordDao;
    private UserService userService;
    private LevelService levelService;
    private TopicService topicService;
    private WordService wordService;

    @BeforeEach
    public void setUp(){
        this.wordDao = mock(WordDao.class);
        this.userService = mock(UserService.class);
        this.levelService = mock(LevelService.class);
        this.topicService = mock(TopicService.class);
        this.wordService = new WordService(wordDao, topicService, levelService, userService);
    }
    @Test
    void create() {
        when(userService.getUserId()).thenReturn(1);
        when(levelService.get("Elementary")).thenReturn(LEVEL);
        when(topicService.get("Travel")).thenReturn(TOPIC);
        when(wordDao.get(1,"cat")).thenReturn(null);
        wordService.create("cat","кот","Travel","Elementary");
        verify(wordDao, times(1)).create("cat","кот",1, LEVEL.getId(),TOPIC.getId());
    }


    @Test
    void createWhenWordIsAbsent() {
        when(userService.getUserId()).thenReturn(1);
        when(levelService.get("Elementary")).thenReturn(LEVEL);
        when(topicService.get("Travel")).thenReturn(TOPIC);

        when(wordDao.get(1,"cat")).thenReturn(new Word());
        wordService.create("cat","кот","Travel","Elementary");
        verify(wordDao, times(0)).create("cat","кот",1, LEVEL.getId(),TOPIC.getId());

    }

    @Test
    void getAllWordResponses() {
        when(userService.getUserId()).thenReturn(1);
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);
        when(wordDao.getAll(1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.getAllWordResponses();
        Assert.assertEquals(TestHelper.wordResponses,wordResponses);
    }

    @Test
    void filter() {
        when(userService.getUserId()).thenReturn(1);
        when(levelService.get("Elementary")).thenReturn(LEVEL);
        when(topicService.get("Travel")).thenReturn(TOPIC);

        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);

        when(wordDao.filter(1,1,1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.filter(TOPIC.getTopicName(),LEVEL.getLevelName());
        Assert.assertEquals(TestHelper.wordResponses,wordResponses);
    }

    @Test
    void get() {
        when(userService.getUserId()).thenReturn(1);
        when(wordDao.get(1,"dog")).thenReturn(WORD_ONE);
        Word actual = wordService.get("dog");
        Assert.assertEquals(WORD_ONE,actual);
    }

    @Test
    void remove() {
        when(userService.getUserId()).thenReturn(1);
        wordService.remove(1);
        verify(wordDao,times(1)).remove(1,1);
    }

    @Test
    void removeAll() {
        when(userService.getUserId()).thenReturn(1);
        wordService.removeAll();
        verify(wordDao,times(1)).removeAll(1);
    }

    @Test
    void sort() {
        when(userService.getUserId()).thenReturn(1);
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);

        when(wordDao.sort(1,"word")).thenReturn(words);
        List<WordResponse> wordResponses = wordService.sort("word");
        Assert.assertEquals(TestHelper.wordResponses,wordResponses);
    }

    @Test
    void findWhenWordIsAbsent() {
        when(userService.getUserId()).thenReturn(1);
        when(wordDao.get(1,"pet")).thenReturn(null);
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);
        when(wordDao.getAll(1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.find("pet");
        Assert.assertEquals(TestHelper.wordResponses, wordResponses);
    }


    @Test
    void find() {
        when(userService.getUserId()).thenReturn(1);
        when(wordDao.get(1,"cat")).thenReturn(WORD_TWO);
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);
        when(wordDao.getAll(1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.find("cat");
        Assert.assertEquals(wordResponsesWithFoundWord, wordResponses);
    }
}
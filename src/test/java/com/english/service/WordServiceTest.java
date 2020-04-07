package com.english.service;

import com.english.TestHelper;
import com.english.dao.WordDao;
import com.english.entity.Word;
import com.english.entity.WordResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.english.TestHelper.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WordServiceTest {
    private WordDao wordDao;
    private LevelService levelService;
    private TopicService topicService;
    private WordService wordService;

    @Before
    public void setUp() {
        this.wordDao = mock(WordDao.class);
        this.levelService = mock(LevelService.class);
        this.topicService = mock(TopicService.class);
        this.wordService = new WordService(wordDao, topicService, levelService);
    }

    @Test
    public void createTest() {
        when(levelService.get("Elementary")).thenReturn(LEVEL);
        when(topicService.get("Travel")).thenReturn(TOPIC);
        when(wordDao.get(1, "cat")).thenReturn(null);
        wordService.create(1, "cat", "кот", "Travel", "Elementary");
        verify(wordDao, times(1)).create("cat", "кот", 1, LEVEL.getId(), TOPIC.getId());
    }


    @Test
    public void createWhenWordIsAbsent() {
        when(levelService.get("Elementary")).thenReturn(LEVEL);
        when(topicService.get("Travel")).thenReturn(TOPIC);

        when(wordDao.get(1, "cat")).thenReturn(new Word());
        wordService.create(1, "cat", "кот", "Travel", "Elementary");
        verify(wordDao, times(0)).create("cat", "кот", 1, LEVEL.getId(), TOPIC.getId());

    }

    @Test
    public void getAllWordResponsesTest() {
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);
        when(wordDao.getAll(1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.getAllWordResponses(1);
        Assert.assertEquals(TestHelper.wordResponses, wordResponses);
    }

    @Test
    public void filterTest() {
        when(levelService.get("Elementary")).thenReturn(LEVEL);
        when(topicService.get("Travel")).thenReturn(TOPIC);

        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);

        when(wordDao.filter(1, 1, 1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.filter(1, TOPIC.getTopicName(), LEVEL.getLevelName());
        Assert.assertEquals(TestHelper.wordResponses, wordResponses);
    }

    @Test
    public void getTest() {
        when(wordDao.get(1, "dog")).thenReturn(WORD_ONE);
        Word actual = wordService.get(1, "dog");
        Assert.assertEquals(WORD_ONE, actual);
    }

    @Test
    public void removeTest() {
        wordService.remove(1, 1);
        verify(wordDao, times(1)).remove(1, 1);
    }

    @Test
    public void removeAllTest() {
        wordService.removeAll(1);
        verify(wordDao, times(1)).removeAll(1);
    }

    @Test
    public void sortTest() {
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);

        when(wordDao.order(1, "word", 0, 0)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.order(1, "word");
        Assert.assertEquals(TestHelper.wordResponses, wordResponses);
    }

    @Test
    public void findWhenWordIsAbsentTest() {
        when(wordDao.get(1, "pet")).thenReturn(null);
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);
        when(wordDao.getAll(1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.find(1, "pet");
        Assert.assertEquals(TestHelper.wordResponses, wordResponses);
    }


    @Test
    public void findWhenWordIsPresentTest() {
        when(wordDao.get(1, "cat")).thenReturn(WORD_TWO);
        when(topicService.get(1)).thenReturn(TOPIC);
        when(levelService.get(1)).thenReturn(LEVEL);
        when(wordDao.getAll(1)).thenReturn(words);
        List<WordResponse> wordResponses = wordService.find(1, "cat");
        Assert.assertEquals(wordResponsesWithFoundWord, wordResponses);
    }
}
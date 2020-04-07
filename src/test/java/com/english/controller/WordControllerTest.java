package com.english.controller;

import com.english.entity.WordResponse;
import com.english.service.LevelService;
import com.english.service.TopicService;
import com.english.service.WordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static com.english.TestHelper.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WordControllerTest {

    private MockMvc mockMvc;

    private WordService wordService;
    private TopicService topicService;
    private LevelService levelService;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.wordService = Mockito.mock(WordService.class);
        this.topicService = Mockito.mock(TopicService.class);
        this.levelService = Mockito.mock(LevelService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new WordController(wordService)).build();
        this.objectMapper = new ObjectMapper();

    }

    @Test
    public void getAllTest() throws Exception {
        when(wordService.getAllWordResponses(1)).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        String actual = mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = objectMapper.readValue(actual, WordResponse[].class);

        Assert.assertEquals(2, result.length);
        Assert.assertEquals(result[0].getWord(), "dog");
        Assert.assertEquals(result[1].getWord(), "cat");
    }

    @Test
    public void addTest() throws Exception {
        mockMvc.perform(post("/add")
                .param("userId", "1")
                .param("word", "cat")
                .param("translate", "кот")
                .param("topic", "Other")
                .param("level", "Elementary"))
                .andExpect(status().isOk());
        verify(wordService, times(1)).create(1, "cat", "Other", "Elementary");
    }

    @Test
    public void removeTest() throws Exception {
        mockMvc.perform(delete("/remove")
                .param("userId", "1")
                .param("id", "1"))
                .andExpect(status().isOk());
        verify(wordService, times(1)).remove(1, 1);
    }

    @Test
    public void removeAllTest() throws Exception {
        mockMvc.perform(delete("/removeAll")
                .param("userId", "1"))
                .andExpect(status().isOk());
        verify(wordService, times(1)).removeAll(1);
    }

    @Test
    public void orderTest() throws Exception {
        when(wordService.order(1, "translate")).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        String actual = mockMvc.perform(get("/order")
                .param("userId", "1")
                .param("order", "translate"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        WordResponse[] result = objectMapper.readValue(actual, WordResponse[].class);
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void filterTest() throws Exception {
        when(wordService.filter(1, "Travel", "Elementary")).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        String actual = mockMvc.perform(get("/filter")
                .param("userId", "1")
                .param("topic", "Travel")
                .param("level", "Elementary"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        WordResponse[] result = objectMapper.readValue(actual, WordResponse[].class);
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void findTest() throws Exception {
        when(wordService.find(1, "cat")).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        String actual = mockMvc.perform(get("/find")
                .param("userId", "1")
                .param("searchedWord", "cat"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        WordResponse[] result = objectMapper.readValue(actual, WordResponse[].class);
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void findWhenWordIsAbsentTest() throws Exception {
        when(wordService.find(1, "cat")).thenReturn(null);
        when(wordService.getAllWordResponses(1)).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        String actual = mockMvc.perform(get("/find")
                .param("userId", "1")
                .param("searchedWord", "cat"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        WordResponse[] result = objectMapper.readValue(actual, WordResponse[].class);
        Assert.assertEquals(2, result.length);
        verify(wordService, times(1)).getAllWordResponses(1);
    }
}
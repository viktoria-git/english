package com.english.controller;

import com.english.entity.Level;
import com.english.entity.Topic;
import com.english.service.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.english.TestHelper.topicList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TopicControllerTest {
    private MockMvc mockMvc;

    private TopicService topicService;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.topicService = Mockito.mock(TopicService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TopicController(topicService)).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void getAll() throws Exception {
        when(topicService.getAll()).thenReturn(topicList);
        String actual = mockMvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Topic[] result = objectMapper.readValue(actual, Topic[].class);

        Assert.assertEquals(1, result.length);
        Assert.assertEquals(result[0].getTopicName(), "Travel");
    }
}
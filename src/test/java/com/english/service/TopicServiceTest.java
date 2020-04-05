package com.english.service;

import com.english.dao.TopicDao;
import com.english.entity.Topic;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static com.english.TestHelper.*;
import static org.mockito.Mockito.*;

class TopicServiceTest {

    private TopicDao topicDao;
    private TopicService topicService;

    @BeforeEach
    void setUp() {
        this.topicDao = Mockito.mock(TopicDao.class);
        this.topicService = new TopicService(topicDao);
    }

    @Test
    void getAll() {
        when(topicDao.getAll()).thenReturn(topicList);
        List<Topic> topics = topicService.getAll();
        Assert.assertEquals(topicList, topics);
    }

    @Test
    void getByName() {
        when(topicDao.get("Travel")).thenReturn(TOPIC);
        Topic actual = topicService.get("Travel");
        Assert.assertEquals(TOPIC, actual);
    }

    @Test
    void getById() {
        when(topicDao.get(1)).thenReturn(TOPIC);
        Topic actual = topicService.get(1);
        Assert.assertEquals(TOPIC, actual);
    }
}
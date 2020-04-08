package com.english.service;

import com.english.dao.TopicDao;
import com.english.entity.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.english.utils.TestHelper.topicList;
import static org.mockito.Mockito.when;

public class TopicServiceTest {

    private TopicDao topicDao;
    private TopicService topicService;

    @Before
    public void setUp() {
        this.topicDao = Mockito.mock(TopicDao.class);
        this.topicService = new TopicService(topicDao);
    }

    @Test
    public void getAllTest() {
        when(topicDao.getAll()).thenReturn(topicList);
        List<Topic> topics = topicService.getAll();
        Assert.assertEquals(topicList, topics);
    }

    @Test
    public void getByNameTest() {
        when(topicDao.get("Travel")).thenReturn(topicList.get(0));
        Topic actual = topicService.get("Travel");
        Assert.assertEquals(topicList.get(0), actual);
    }

    @Test
    public void getByIdTest() {
        when(topicDao.get(1)).thenReturn(topicList.get(0));
        Topic actual = topicService.get(1);
        Assert.assertEquals(topicList.get(0), actual);
    }
}
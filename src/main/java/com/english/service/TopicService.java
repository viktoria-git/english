package com.english.service;

import com.english.dao.TopicDao;
import com.english.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicDao topicDao;

    @Autowired
    public TopicService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public List<Topic> getAll() {
        return topicDao.getAll();
    }

    public Topic getById(Integer id) {return topicDao.getById(id);}

    public Topic getByName(String topic) {return topicDao.getByName(topic);}
}

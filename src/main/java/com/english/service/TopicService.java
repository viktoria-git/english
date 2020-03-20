package com.english.service;

import com.english.dao.TopicDao;
import com.english.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private TopicDao topicDao;

    @Autowired
    public TopicService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public List<Topic> getAll() {
        return topicDao.getAll();
    }

    public Topic get(int id) {return topicDao.get(id);}

}

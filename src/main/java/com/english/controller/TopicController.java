package com.english.controller;

import com.english.entity.Topic;
import com.english.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    public List<Topic> getAll() {
        return topicService.getAll();
    }
}

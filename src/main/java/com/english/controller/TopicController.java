package com.english.controller;

import com.english.entity.Topic;
import com.english.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(path = "/topics")
    public List<Topic> getAll() {
        LOGGER.info("Get all topics");
        return topicService.getAll();
    }
}

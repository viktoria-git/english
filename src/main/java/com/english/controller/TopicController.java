package com.english.controller;

import com.english.entity.Topic;
import com.english.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @ResponseBody
    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    public List<Topic> getAll() {
        return topicService.getAll();
    }
}

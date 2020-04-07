package com.english.controller;

import com.english.entity.Level;
import com.english.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LevelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LevelController.class);
    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(path = "/levels")
    public List<Level> getAll() {
        LOGGER.info("Get all levels");
        return levelService.getAll();
    }
}

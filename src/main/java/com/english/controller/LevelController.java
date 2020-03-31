package com.english.controller;

import com.english.entity.Level;
import com.english.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @ResponseBody
    @RequestMapping(path = "/levels", method = RequestMethod.GET)
    public List<Level> getAll() {
        return levelService.getAll();
    }
}

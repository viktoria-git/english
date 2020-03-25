package com.english.service;

import com.english.dao.LevelDao;
import com.english.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {

    private final LevelDao levelDao;

    @Autowired
    public LevelService(LevelDao levelDao) {
        this.levelDao = levelDao;
    }

    public List<Level> getAll() {
        return levelDao.getAll();
    }

    public Level getById(Integer id) {return levelDao.getById(id);}

    public Level getByName(String level) {return levelDao.getByName(level);}
}

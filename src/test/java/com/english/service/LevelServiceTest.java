package com.english.service;

import com.english.dao.LevelDao;
import com.english.entity.Level;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.english.TestHelper.LEVEL;
import static com.english.TestHelper.levelList;
import static org.mockito.Mockito.when;

class LevelServiceTest {

    private LevelDao levelDao;
    private LevelService levelService;

    @BeforeEach
    void setUp() {
        this.levelDao = Mockito.mock(LevelDao.class);
        this.levelService = new LevelService(levelDao);
    }

    @Test
    void getAll() {
        when(levelDao.getAll()).thenReturn(levelList);
        List<Level> actualList = levelService.getAll();
        Assert.assertEquals(levelList,actualList);
    }

    @Test
    void getByName() {
        when(levelDao.get("Elementary")).thenReturn(LEVEL);
        Level actualResult = levelService.get("Elementary");
        Assert.assertEquals(LEVEL,actualResult);
    }

    @Test
    void getById() {
        when(levelDao.get(1)).thenReturn(LEVEL);
        Level actualResult = levelService.get(1);
        Assert.assertEquals(LEVEL,actualResult);
    }
}
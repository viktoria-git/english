package com.english.service;

import com.english.dao.LevelDao;
import com.english.entity.Level;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.english.utils.TestHelper.levelList;
import static org.mockito.Mockito.when;

public class LevelServiceTest {
    private LevelDao levelDao;
    private LevelService levelService;

    @Before
    public void setUp() {
        this.levelDao = Mockito.mock(LevelDao.class);
        this.levelService = new LevelService(levelDao);
    }

    @Test
    public void getAll() {
        when(levelDao.getAll()).thenReturn(levelList);
        List<Level> actualList = levelService.getAll();
        Assert.assertEquals(levelList, actualList);
    }

    @Test
    public void getByName() {
        when(levelDao.get("Elementary")).thenReturn(levelList.get(0));
        Level actualResult = levelService.get("Elementary");
        Assert.assertEquals(levelList.get(0), actualResult);
    }

    @Test
    public void getById() {
        when(levelDao.get(1)).thenReturn(levelList.get(0));
        Level actualResult = levelService.get(1);
        Assert.assertEquals(levelList.get(0), actualResult);
    }
}
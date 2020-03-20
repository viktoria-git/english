package com.english.dao;

import com.english.entity.Level;

import java.util.List;

public interface LevelDao {
    List<Level> getAll();
    Level get(Integer id);
}

package com.english.dao;

import com.english.entity.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> getAll();
    Topic getById(Integer id);
    Topic getByName(String topic);
}
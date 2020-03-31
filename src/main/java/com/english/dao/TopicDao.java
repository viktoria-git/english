package com.english.dao;

import com.english.entity.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> getAll();

    Topic get(String topic);

    Topic get(Integer id);
}
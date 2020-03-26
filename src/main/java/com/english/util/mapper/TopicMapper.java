package com.english.util.mapper;

import com.english.entity.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicMapper implements RowMapper<Topic> {

    @Override
    public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt("id"));
        topic.setTopicName(resultSet.getString("topic_name"));
        topic.setColor(resultSet.getString("color"));
        return topic;
    }

}

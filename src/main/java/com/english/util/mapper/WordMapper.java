package com.english.util.mapper;

import com.english.entity.Word;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordMapper implements RowMapper<Word> {

    @Override
    public Word mapRow(ResultSet resultSet, int i) throws SQLException {
        Word word = new Word();
        word.setId(resultSet.getInt("id"));
        word.setWord(resultSet.getString("word"));
        word.setTranslate(resultSet.getString("translate"));
        word.setTopicId(resultSet.getInt("user_id"));
        word.setTopicId(resultSet.getInt("topic_id"));
        word.setLevelId(resultSet.getInt("level_id"));
        return word;
    }

}

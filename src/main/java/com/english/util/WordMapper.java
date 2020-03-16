package com.english.util;

import com.english.entity.Word;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordMapper implements RowMapper<Word>{

    @Override
    public Word mapRow(ResultSet resultSet, int i) throws SQLException {
        Word word = new Word();
        word.setId(resultSet.getInt("id"));
        word.setWord(resultSet.getString("word"));
        word.setTranslate(resultSet.getString("translate"));
        word.setColor(resultSet.getString("color"));
        return word;
    }
}

package com.english.util.mapper;

import com.english.entity.Level;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelMapper implements RowMapper<Level> {
    @Override
    public Level mapRow(ResultSet resultSet, int i) throws SQLException {
        Level level = new Level();
        level.setId(resultSet.getInt("id"));
        level.setLevelName(resultSet.getString("level_name"));
        return level;
    }
}

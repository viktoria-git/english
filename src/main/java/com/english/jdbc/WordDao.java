package com.english.jdbc;

import com.english.entity.Word;
import com.english.util.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class WordDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(String word, String translate, String color) {
        String sql = "INSERT INTO word(word,translate,color) VALUES(?,?,?)";
        jdbcTemplate.update(sql,word,translate,color);
    }

    public List<Word> getAll() {
        String sql = "SELECT * FROM word";
        return jdbcTemplate.query(sql,new WordMapper());
    }

    public Word get(String word) {
        String sql = "SELECT * FROM word WHERE word=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{word},new WordMapper());
    }

    public Word getById(Integer id) {
        String sql = "SELECT * FROM word WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new WordMapper());
    }

    public void remove(Integer id) {
        String sql = "DELETE FROM word WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}

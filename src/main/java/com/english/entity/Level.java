package com.english.entity;

import java.util.Objects;

public class Level {
    private Integer id;
    private String levelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return Objects.equals(id, level.id) &&
                Objects.equals(levelName, level.levelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, levelName);
    }
}

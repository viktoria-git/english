package com.english.entity;

import java.util.Objects;

public class WordResponse {
    private Integer id;
    private String word;
    private String translate;
    private String color;
    private boolean allocated;
    private Integer topicId;
    private String topicName;
    private Integer levelId;
    private String levelName;

    public WordResponse() {
    }

    public WordResponse(Word word, Topic topic, Level level) {
        this.id = word.getId();
        this.word = word.getWord();
        this.translate = word.getTranslate();
        this.allocated = false;
        this.color = topic.getColor();
        this.topicId = word.getTopicId();
        this.topicName = topic.getTopicName();
        this.levelId = level.getId();
        this.levelName = level.getLevelName();
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordResponse wordResponse = (WordResponse) o;
        return allocated == wordResponse.allocated &&
                Objects.equals(id, wordResponse.id) &&
                Objects.equals(word, wordResponse.word) &&
                Objects.equals(translate, wordResponse.translate) &&
                Objects.equals(color, wordResponse.color) &&
                Objects.equals(topicId, wordResponse.topicId) &&
                Objects.equals(topicName, wordResponse.topicName) &&
                Objects.equals(levelId, wordResponse.levelId) &&
                Objects.equals(levelName, wordResponse.levelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translate, color, allocated, topicId, topicName, levelId, levelName);
    }
}

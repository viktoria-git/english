package com.english.entity;

import java.util.Objects;

public class WordResponse {
    private Integer id;
    private String word;
    private String translate;
    private String color;
    private boolean allocated;
    private String topic;
    private String level;

    public WordResponse(Word word, Topic topic, Level level) {
        this.id = word.getId();
        this.word = word.getWord();
        this.translate = word.getTranslate();
        this.allocated = false;
        this.color = topic.getColor();
        this.topic = topic.getTopicName();
        this.level = level.getLevelName();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
                Objects.equals(topic, wordResponse.topic) &&
                Objects.equals(level, wordResponse.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translate, color, allocated, topic, level);
    }
}

package com.english.entity;

public class Word {
    private Integer id;
    private String word;
    private String translate;
    private String color;
    private Integer topicId;
    private Integer levelId;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(id, word.id) &&
                Objects.equals(this.word, word.word) &&
                Objects.equals(translate, word.translate) &&
                Objects.equals(color, word.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translate, color);
    }

    @Override
    public String toString() {
        return "Word" + id + ","
                + word + ","
                + translate + ","
                + color;
    }

}

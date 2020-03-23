package com.english.entity;

import java.util.Objects;

public class WordResponse {
    private Integer id;
    private String word;
    private String translate;
    private String color;
    private boolean allocated;
    private Integer userId;

    public WordResponse(Word word) {
        this.id = word.getId();
        this.word = word.getWord();
        this.translate = word.getTranslate();
        this.color = word.getColor();
        this.allocated = false;
        this.userId = word.getUserId();
    }

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
        WordResponse that = (WordResponse) o;
        return allocated == that.allocated &&
                Objects.equals(id, that.id) &&
                Objects.equals(word, that.word) &&
                Objects.equals(translate, that.translate) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translate, color, allocated, userId);
    }
}

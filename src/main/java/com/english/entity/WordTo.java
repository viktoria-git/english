package com.english.entity;

public class WordTo {
    private Integer id;
    private String word;
    private String translate;
    private String color;
    private boolean isAllocated;

    public WordTo(Word word) {
        this.id = word.getId();
        this.word = word.getWord();
        this.translate = word.getTranslate();
        this.color = word.getColor();
        this.isAllocated = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() { return word; }

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
        return isAllocated;
    }

    public void setAllocated(boolean allocated) {
        isAllocated = allocated;
    }
}

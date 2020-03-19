package com.english.entity;

public class WordTo {
    private Integer id;
    private String word;
    private String translate;
    private String color;
    private boolean allocated;
    private Integer topicId;
    private String topicName;

    public WordTo(Word word, Topic topic) {
        this.id = word.getId();
        this.word = word.getWord();
        this.translate = word.getTranslate();
        this.color = topic.getColor();
        this.topicId = word.getTopicId();
        this.topicName = topic.getTopicName();
        this.allocated = false;
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
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }
}

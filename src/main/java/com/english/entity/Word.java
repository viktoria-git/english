package com.english.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String word;
    private String translate;
    private String color = Color.valueOf(randColor()).getFieldName();

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

    private int randColor() {
        return 1 + (int) (Math.random() * 7);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(id, word1.id) &&
                Objects.equals(word, word1.word) &&
                Objects.equals(translate, word1.translate) &&
                Objects.equals(color, word1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translate, color);
    }
}

package com.english.entity;

import java.util.Objects;

public class Topic {
    private int id;
    private String topicName;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                Objects.equals(topicName, topic.topicName) &&
                Objects.equals(color, topic.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicName, color);
    }
}

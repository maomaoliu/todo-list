package com.thoughtworks.campus.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {
    private long id;
    private String content;
    private boolean isComplete;
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(Long id,
                String content,
                boolean isComplete) {
        this.id = id;
        this.content = content;
        this.isComplete = isComplete;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
    }
}

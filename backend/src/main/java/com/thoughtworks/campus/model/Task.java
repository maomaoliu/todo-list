package com.thoughtworks.campus.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {
    private long id;
    private String content;
    private TaskStatus status;
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(Long id,
                String content,
                TaskStatus status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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

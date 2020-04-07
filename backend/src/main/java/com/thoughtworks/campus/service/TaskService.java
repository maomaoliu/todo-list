package com.thoughtworks.campus.service;

import com.thoughtworks.campus.model.Task;
import com.thoughtworks.campus.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    public TaskStore store;

    public List<Task> getAll() {
        return store.readTasks();
    }

    public Task saveTask(Task task) {
        List<Task> tasks = new ArrayList<>(store.readTasks());
        task.setUpdatedAt();
        tasks.add(task);
        store.writeTasks(tasks);
        return task;
    }

    public Optional<Task> find(Long id) {
        return store.readTasks().stream().filter(task -> task.getId() == id).findAny();
    }

    public Optional<Task> update(Task task) {
        List<Task> tasks = new ArrayList<>(store.readTasks());
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == task.getId()).findAny();
        if (any.isPresent()) {
            any.get().setContent(task.getContent());
            any.get().setUpdatedAt();
            store.writeTasks(tasks);
        }
        return any;
    }

    public Optional<Task> delete(Long id) {
        List<Task> tasks = store.readTasks();
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == id).findAny();
        if (any.isPresent()) {
            store.writeTasks(tasks.stream().filter(task -> task.getId() != id).collect(Collectors.toList()));
            return any;
        }
        return any;
    }
}

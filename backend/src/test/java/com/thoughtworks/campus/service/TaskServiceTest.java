package com.thoughtworks.campus.service;

import com.thoughtworks.campus.model.Task;
import com.thoughtworks.campus.store.TaskStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {
    @Mock
    private TaskStore taskStore;

    @InjectMocks
    private TaskService taskService = new TaskService();

    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
    }

    @Test
    public void shouldSaveTask() {
        when(taskStore.readTasks()).thenReturn(tasks);

        Task savedTask = taskService.saveTask(new Task(1L, "newTask"));

        assertNotNull(savedTask.getUpdatedAt());
        verify(taskStore).writeTasks(any());
    }

    @Test
    public void shouldGetAllTasks() {
        when(taskStore.readTasks()).thenReturn(tasks);

        List<Task> all = taskService.getAll();

        assertEquals(tasks, all);
    }

    @Test
    public void shouldFindTask() {
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.find(1L);

        Task task = optionalTask.get();
        assertEquals(1L, task.getId());
        assertEquals("task", task.getContent());
    }

    @Test
    public void shouldGetEmptyTask() {
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.find(1L);

        assertFalse(optionalTask.isPresent());
    }

    @Test
    public void shouldUpdateTask() {
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.update(new Task(1L, "new task"));

        Task task = optionalTask.get();
        assertEquals(1L, task.getId());
        assertEquals("new task", task.getContent());
        assertNotNull(task.getUpdatedAt());
        verify(taskStore).writeTasks(any());
    }

    @Test
    public void shouldNotUpdateTaskWhenNotExist() {
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.update(new Task(1L, "new task"));

        assertFalse(optionalTask.isPresent());
        verify(taskStore, new Times(0)).writeTasks(any());
    }

    @Test
    public void shouldDeleteTask() {
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.delete(1L);

        Task task = optionalTask.get();
        assertEquals(1L, task.getId());
        verify(taskStore).writeTasks(any());
    }

    @Test
    public void shouldNotDeleteTaskWhenNotExist() {
        when(taskStore.readTasks()).thenReturn(tasks);

        Optional<Task> optionalTask = taskService.delete(1L);

        assertFalse(optionalTask.isPresent());
        verify(taskStore, new Times(0)).writeTasks(any());
    }
}

package com.thoughtworks.campus;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TasksIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void noParamTasksShouldReturnAllTasksFromService() throws Exception {
        this.mockMvc.perform(get("/api/tasks")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("test"));
    }

    @Test
    @Order(2)
    public void shouldSaveTask() throws Exception {
        this.mockMvc.perform(post("/api/tasks")
                .content("{ \"id\" : 2, \"content\" : \"check if it works\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldGetTaskByTaskId() throws Exception {
        this.mockMvc.perform(get("/api/tasks/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("check if it works"));
    }

    @Test
    @Order(4)
    public void shouldUpdateContentByTaskId() throws Exception {
        this.mockMvc.perform(put("/api/tasks/2")
                .content("{ \"content\" : \"it should work\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("it should work"));
    }

    @Test
    @Order(5)
    public void shouldDeleteByTaskId() throws Exception {
        this.mockMvc.perform(delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    public void shouldGetNotFoundWhenTaskDoesNotExist() throws Exception {
        this.mockMvc.perform(delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound());
    }
}

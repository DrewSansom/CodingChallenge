package com.example.userapplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    public void getUserPageShouldReturnAllUsers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getUserByIDShouldReturnUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getInvalidUserByIDShouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}","404"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    public void postValidUserShouldReturnSucess() throws Exception {
        User user = new User("Sara", "Test");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(5)
    public void postExistingUserShouldReturnError() throws Exception {
        User user = new User("Sara", "Test");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(6)
    public void deleteValidUserShouldReturnValid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{id}", "1"))
                .andExpect(status().isNoContent());

    }

    @Test
    @Order(7)
    public void deleteInvalidUserShouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{id}", "1"))
                .andExpect(status().isGone());
    }

}
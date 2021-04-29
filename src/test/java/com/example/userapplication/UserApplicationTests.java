package com.example.userapplication;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.ExecutionException;

@SpringBootTest
@AutoConfigureMockMvc
class UserApplicationTests {


    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getUserPageShouldReturnAllUsers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByIDShouldReturnUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getInvalidUserByIDShouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}","44"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postValidUserShouldReturnSucess() throws Exception {
        User user = new User(Long.valueOf(5), "Sara", "Test");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteValidUserShouldReturnValid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{id}", "1"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteInvalidUserShouldReturnInvalid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{id}", "1"))
                .andExpect(status().isGone());
    }

}
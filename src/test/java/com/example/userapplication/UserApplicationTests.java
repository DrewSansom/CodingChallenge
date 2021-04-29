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


/**
 * Test for the controller methods in the UserApplication
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // Used to wrap data for post requests in JSON form
    private ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Tests GET request for all users
     * @throws Exception
     */
    @Test
    @Order(1)
    public void getUserPageShouldReturnAllUsers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andExpect(status().isOk());
    }

    /**
     * Tests GET reqeust for single user
     * @throws Exception
     */
    @Test
    @Order(2)
    public void getUserByIDShouldReturnUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}", "1"))
                .andExpect(status().isOk());
    }

    /**
     * Tests GET request for invalid/nonexistant user
     * @throws Exception
     */
    @Test
    @Order(3)
    public void getInvalidUserByIDShouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}","404"))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests POST request for creating new valied user
     * @throws Exception
     */
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

    /**
     * Tests POST request for user that already exists
     * @throws Exception
     */
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

    /**
     * Tests deleting a user that exists
     * @throws Exception
     */
    @Test
    @Order(6)
    public void deleteValidUserShouldReturnValid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{id}", "1"))
                .andExpect(status().isNoContent());

    }

    /**
     * Tests deleting a use that doesn't exist
     * @throws Exception
     */
    @Test
    @Order(7)
    public void deleteInvalidUserShouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/{id}", "1"))
                .andExpect(status().isGone());
    }

}
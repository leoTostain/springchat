package com.leocorp.springchat.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUserNoUsernameThrowsIAE() throws Exception {
        this.mockMvc.perform(post("/addUser").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserBadUsernameThrowsIAE() throws Exception {
        this.mockMvc.perform(post("/addUser").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username","")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserReturnUser() throws Exception {
        this.mockMvc.perform(post("/addUser").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .param("username","test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.uuid").exists());
    }

    @WithMockUser(authorities="USER")
    @Test
    void deleteUserNoUuidThrowsIAE() throws Exception {
        this.mockMvc.perform(delete("/removeUser").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(authorities="USER")
    @Test
    void deleteUserBadUuidThrowsIAE() throws Exception {
        this.mockMvc.perform(delete("/removeUser").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid", "not an uuid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(authorities="USER")
    @Test
    void deleteUserRandomUuidThrowsUNFE() throws Exception {
        this.mockMvc.perform(delete("/removeUser").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid", UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(authorities="USER")
    @Test
    void deleteUserStatusNoContent() throws Exception {
        this.mockMvc.perform(delete("/removeUser").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid","ea5b550e-0b1f-4f51-bb7f-b4c3e023abc6")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserNoUuidThrowsIAE() throws Exception {
        this.mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserBadUuidThrowsIAE() throws Exception {
        this.mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid", "not an uuid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserRandomUuidThrowsUNFE() throws Exception {
        this.mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid", UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserReturnUser() throws Exception {
        this.mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid","dde47cf4-973a-45b3-b72f-39871c6c53de")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.uuid").value("dde47cf4-973a-45b3-b72f-39871c6c53de"));
    }

    @Test
    void getUsersReturnUsers() throws Exception {
        this.mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uuid","dde47cf4-973a-45b3-b72f-39871c6c53de")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].uuid").value("dde47cf4-973a-45b3-b72f-39871c6c53de"))
                .andExpect(jsonPath("$[1].username").value("user2"))
                .andExpect(jsonPath("$[1].uuid").value("957cbde3-7673-4bcc-8aa5-e6f861087a83"))
                .andExpect(jsonPath("$[2].username").value("user3"))
                .andExpect(jsonPath("$[2].uuid").value("034dbe7c-99af-41ac-8907-8318189a6eee"))
                .andExpect(jsonPath("$[3].username").value("user4"))
                .andExpect(jsonPath("$[3].uuid").value("ea5b550e-0b1f-4f51-bb7f-b4c3e023abc6"));
    }
}


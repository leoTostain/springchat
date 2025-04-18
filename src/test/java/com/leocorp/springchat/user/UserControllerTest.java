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
    void signInNoCredentialThrowsIAE() throws Exception {
        this.mockMvc.perform(post("/signIn").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signInBadCredentialThrowsIAE() throws Exception {
        this.mockMvc.perform(post("/signIn").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username","")
                        .param("password","")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signInReturnUserPrivateInfo() throws Exception {
        this.mockMvc.perform(post("/signIn").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .param("username","username")
                .param("password","password")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.uuid").exists());
    }

    @Test
    void loginReturnUserPrivateInfo() throws Exception {
        this.mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON).with(csrf())
                        .param("username","user1")
                        .param("password","password1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
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
                        .param("username", "notAUser")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserReturnUserPublicInfo() throws Exception {
        this.mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username","user1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("user1"));
    }

    @Test
    void getUsersReturnUsersPublicInfo() throws Exception {
        this.mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[1].username").value("user2"))
                .andExpect(jsonPath("$[2].username").value("user3"))
                .andExpect(jsonPath("$[3].username").value("user4"));
    }
}


package com.leocorp.springchat.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginBadCredentials() throws Exception {
        this.mockMvc.perform(formLogin()
                        .password("abc"))
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    void loginUserNotFound() throws Exception {
        this.mockMvc.perform(formLogin()
                        .user("user")
                        .password("password"))
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    void loginIsOk() throws Exception {
        this.mockMvc.perform(formLogin()
                        .user("user1")
                        .password("password1"))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }
}


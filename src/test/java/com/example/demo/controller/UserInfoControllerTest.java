package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
public class UserInfoControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(ctx).build();
    }

    @Test
    void profile() throws Exception {
        mockMvc.perform(get("/user-info/profile/all")).andExpect(status().isOk()).andDo(print());
    }

    // TODO need to test more for exception cases
}
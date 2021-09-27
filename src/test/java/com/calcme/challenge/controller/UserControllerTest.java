package com.calcme.challenge.controller;

import com.calcme.challenge.model.User;
import com.calcme.challenge.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    public void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("000", "Bruno Couto Matsuo", "brunocomat@gmail.com", "1199999999"));

        Mockito.when(service.findAll()).thenReturn(users);

        MvcResult response = mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(response);
    }

    @Test
    public void createUser() throws Exception {
        User user = new User("000", "Bruno Couto Matsuo", "brunocomat@gmail.com", "1199999999");

        Mockito.when(service.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(user.getId())))
                .andExpect(jsonPath("$.name", equalTo(user.getName())))
                .andExpect(jsonPath("$.email", equalTo(user.getEmail())))
                .andExpect(jsonPath("$.phone", equalTo(user.getPhone())));
    }

}

package com.example.roles.conroller;

import com.example.roles.dto.ResultDTO;
import com.example.roles.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void addUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("login");
        userDTO.setPassword("password");
        userDTO.setName("Andrei");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    ResultDTO resultDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResultDTO.class);
                    Assertions.assertThat(resultDTO).isNotNull();
                    Assertions.assertThat(resultDTO.getErrors()).isNull();
                    Assertions.assertThat(resultDTO.isSuccess()).isTrue();
                });
        mockMvc.perform(MockMvcRequestBuilders.get("/user/"+userDTO.getLogin())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    UserDTO user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);
                    Assertions.assertThat(user.getLogin()).isEqualTo(userDTO.getLogin());
                    Assertions.assertThat(user.getName()).isEqualTo(userDTO.getName());
                    Assertions.assertThat(user.getPassword()).isEqualTo(userDTO.getPassword());
                    Assertions.assertThat(user.getUserRole()).isEqualTo(userDTO.getUserRole());
                });

    }


    @Test
    void getUserByLogin() {
    }

    @Test
    void getAllUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<UserDTO> userDTOList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<UserDTO>>() {
                    });
                    Assertions.assertThat(userDTOList).isEmpty();
                });
    }
}
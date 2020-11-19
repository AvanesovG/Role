package com.example.roles.service;

import com.example.roles.dto.UserDTO;

import java.util.List;

public interface UserService {
    void add(UserDTO userDTO);

    void delete(String login);

    void update(UserDTO userDTO);

    UserDTO get(String login);

    List<UserDTO> getAll();
}

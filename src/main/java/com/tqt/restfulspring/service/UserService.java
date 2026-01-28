package com.tqt.restfulspring.service;

import com.tqt.restfulspring.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUser(String username);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    int deleteUser(String username);
    boolean login(String username, String password);
    List<UserDTO> getAllUsers();
}

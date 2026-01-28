package com.tqt.restfulspring.service.impl;

import com.tqt.restfulspring.dto.UserDTO;
import com.tqt.restfulspring.entity.Role;
import com.tqt.restfulspring.entity.User;
import com.tqt.restfulspring.repository.UserRepository;
import com.tqt.restfulspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User newUser = user.get();
            return new UserDTO(newUser.getUsername(), newUser.getPassword(), newUser.getEmail(), newUser.getRole().getId(), newUser.getRole().getName());
        }
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserDTO newUserDTO = new UserDTO();
        Role role = new Role();
        role.setName("ROLE_USER");
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), role);
        userRepository.save(user);
        newUserDTO.setUserId(user.getUserId());
        return newUserDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public int deleteUser(String username) {
        return 0;
    }

    @Override
    public UserDTO login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole().getName());
        return dto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            return new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole().getId(), user.getRole().getName());
        }).toList();
    }

    @Override
    public UserDTO findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }
}

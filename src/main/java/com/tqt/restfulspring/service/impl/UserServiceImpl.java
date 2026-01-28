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
    public boolean login(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            User user = userRepository.findByUsernameAndPassword(username, password);
            if (user != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            return new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole().getId(), user.getRole().getName());
        }).toList();
    }
}

package com.tqt.restfulspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private int userId;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private String role;

    public UserDTO(String username, String password, String email, int roleId, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.role = role;
    }
}

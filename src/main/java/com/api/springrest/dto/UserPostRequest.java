package com.api.springrest.dto;

import com.api.springrest.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostRequest {

    private Long id;
    private String username;
    private String password;
    private String email;

    public User convert(UserPostRequest dto) {
        return User
                .builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .build();
    }
}

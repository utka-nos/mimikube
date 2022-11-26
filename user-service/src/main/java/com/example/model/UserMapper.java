package com.example.model;

import com.example.dto.Mapper;
import com.example.dto.UserDTO;

public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getUsername());
        user.setName(dto.getUsername());
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getLogin());
        return userDTO;
    }
}

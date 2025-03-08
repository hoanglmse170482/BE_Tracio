package com.tracio.Tracio.mapper;

import com.tracio.Tracio.dto.request.UserDTO;
import com.tracio.Tracio.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getPhone(),
                user.getStatus()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPhone(userDTO.getPhone());
        user.setStatus(userDTO.getStatus());
        return user;
    }
}


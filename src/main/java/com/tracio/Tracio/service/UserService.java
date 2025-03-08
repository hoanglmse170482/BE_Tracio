package com.tracio.Tracio.service;

import com.tracio.Tracio.repository.UserRepository;
import com.tracio.Tracio.dto.request.UserDTO;
import com.tracio.Tracio.mapper.UserMapper;
import com.tracio.Tracio.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::toDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        if (user.getStatus() == null) {
            user.setStatus("active");
        }
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if(userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if(userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }
        if(userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getStatus() != null) {
            user.setStatus(userDTO.getStatus());
        }

        userRepository.save(user);

        return UserMapper.toDTO(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}

package com.taskmanager.service;

import com.taskmanager.dto.UserRequestDto;
import com.taskmanager.dto.UserResponseDto;
import com.taskmanager.entity.User;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // CREATE USER
    public UserResponseDto createUser(UserRequestDto dto) {

        log.info("Creating user with email: {}", dto.getEmail());
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return mapToResponse(savedUser);
    }

    // GET ALL USERS
    public List<UserResponseDto> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    // GET USER BY ID
    public UserResponseDto getUserById(Long id) {

        log.info("Fetching user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToResponse(user);
    }

    // ENTITY -> RESPONSE DTO
    private UserResponseDto mapToResponse(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
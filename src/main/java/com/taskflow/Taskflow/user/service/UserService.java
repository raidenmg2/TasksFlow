package com.taskflow.Taskflow.user.service;

import com.taskflow.Taskflow.user.dto.CreateUserRequest;
import com.taskflow.Taskflow.user.dto.UpdateUserRequest;
import com.taskflow.Taskflow.user.dto.UserResponse;
import com.taskflow.Taskflow.user.entity.User;
import com.taskflow.Taskflow.user.entity.UserRole;
import com.taskflow.Taskflow.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserResponse getById(UUID id) {
        return toResponse(findById(id));
    }

    public UserResponse create(CreateUserRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole(UserRole.USER);
        user.setActive(true);

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    public UserResponse update(UUID id, UpdateUserRequest request) {

        User existingUser = findById(id);

        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return toResponse(updatedUser);
    }

    public void delete(UUID id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    private UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setActive(user.getActive());

        return response;
    }
}
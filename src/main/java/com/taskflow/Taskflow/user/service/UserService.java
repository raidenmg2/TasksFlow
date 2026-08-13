package com.taskflow.Taskflow.user.service;

import com.taskflow.Taskflow.user.entity.User;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(UUID id, User user) {
        User existingUser = findById(id);

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());


        return userRepository.save(existingUser);
    }

    public void delete(UUID id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
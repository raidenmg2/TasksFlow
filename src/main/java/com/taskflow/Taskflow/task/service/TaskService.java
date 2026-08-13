package com.taskflow.Taskflow.task.service;

import com.taskflow.Taskflow.task.entity.Task;
import com.taskflow.Taskflow.task.entity.TaskStatus;
import com.taskflow.Taskflow.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    // Actualización completa para administración
    public Task update(UUID id, Task task) {
        Task existingTask = findById(id);

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setDueDate(task.getDueDate());

        return taskRepository.save(existingTask);
    }

    // Actualización permitida al usuario asignado
    public Task updateStatus(UUID id, TaskStatus status) {
        Task existingTask = findById(id);

        existingTask.setStatus(status);

        return taskRepository.save(existingTask);
    }

    public void delete(UUID id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
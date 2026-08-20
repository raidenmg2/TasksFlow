package com.taskflow.Taskflow.task.service;

import com.taskflow.Taskflow.exception.ProjectNotFoundException;
import com.taskflow.Taskflow.exception.UserNotFoundException;
import com.taskflow.Taskflow.project.entity.Project;
import com.taskflow.Taskflow.project.repository.ProjectRepository;
import com.taskflow.Taskflow.task.dto.CreateTaskRequest;
import com.taskflow.Taskflow.task.dto.TaskResponse;
import com.taskflow.Taskflow.task.dto.UpdateTaskRequest;
import com.taskflow.Taskflow.task.dto.UpdateTaskStatusRequest;
import com.taskflow.Taskflow.task.entity.Task;
import com.taskflow.Taskflow.task.entity.TaskStatus;
import com.taskflow.Taskflow.task.repository.TaskRepository;
import com.taskflow.Taskflow.user.entity.User;
import com.taskflow.Taskflow.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.taskflow.Taskflow.exception.TaskNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Task findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Tarea no encontrada"
                ));
    }

    public TaskResponse getById(UUID id) {
        return toResponse(findById(id));
    }

    public TaskResponse create(CreateTaskRequest request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Proyecto no encontrado"
                ));

        User assignedUser = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuario asignado no encontrado"
                ));

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        task.setStatus(TaskStatus.TODO);
        task.setProject(project);
        task.setAssignedUser(assignedUser);

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    public TaskResponse update(UUID id, UpdateTaskRequest request) {

        Task existingTask = findById(id);

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Proyecto no encontrado"
                ));

        User assignedUser = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuario asignado no encontrado"
                ));

        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setPriority(request.getPriority());
        existingTask.setStatus(request.getStatus());
        existingTask.setDueDate(request.getDueDate());

        existingTask.setProject(project);
        existingTask.setAssignedUser(assignedUser);

        Task updatedTask = taskRepository.save(existingTask);

        return toResponse(updatedTask);
    }

    public TaskResponse updateStatus(
            UUID id,
            UpdateTaskStatusRequest request) {

        Task existingTask = findById(id);

        existingTask.setStatus(request.getStatus());

        Task updatedTask = taskRepository.save(existingTask);

        return toResponse(updatedTask);
    }

    public void delete(UUID id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        response.setDueDate(task.getDueDate());

        if (task.getProject() != null) {
            response.setProjectId(task.getProject().getId());
        }

        if (task.getAssignedUser() != null) {
            response.setAssignedUserId(task.getAssignedUser().getId());
        }

        return response;
    }
}
package com.taskflow.Taskflow.project.service;

import com.taskflow.Taskflow.exception.ProjectNotFoundException;
import com.taskflow.Taskflow.exception.UserNotFoundException;
import com.taskflow.Taskflow.project.dto.CreateProjectRequest;
import com.taskflow.Taskflow.project.dto.ProjectResponse;
import com.taskflow.Taskflow.project.dto.UpdateProjectRequest;
import com.taskflow.Taskflow.project.entity.Project;
import com.taskflow.Taskflow.project.entity.ProjectStatus;
import com.taskflow.Taskflow.project.repository.ProjectRepository;
import com.taskflow.Taskflow.user.entity.User;
import com.taskflow.Taskflow.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            UserRepository userRepository) {

        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Project findById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Proyecto no encontrado"
                ));
    }

    public ProjectResponse getById(UUID id) {
        return toResponse(findById(id));
    }

    public ProjectResponse create(CreateProjectRequest request) {

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuario no encontrado"
                ));

        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setStartDate(request.getStartDate());
        project.setDueDate(request.getDueDate());

        project.setOwner(owner);
        project.setStatus(ProjectStatus.PLANNED);

        Project savedProject = projectRepository.save(project);

        return toResponse(savedProject);
    }

    public ProjectResponse update(UUID id, UpdateProjectRequest request) {

        Project existingProject = findById(id);

        existingProject.setName(request.getName());
        existingProject.setDescription(request.getDescription());
        existingProject.setStatus(request.getStatus());
        existingProject.setStartDate(request.getStartDate());
        existingProject.setDueDate(request.getDueDate());

        Project updatedProject = projectRepository.save(existingProject);

        return toResponse(updatedProject);
    }

    public void delete(UUID id) {

        Project project = findById(id);

        projectRepository.delete(project);
    }

    private ProjectResponse toResponse(Project project) {

        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setStatus(project.getStatus());
        response.setStartDate(project.getStartDate());
        response.setDueDate(project.getDueDate());

        if (project.getOwner() != null) {
            response.setOwnerId(project.getOwner().getId());
        }

        return response;
    }
}
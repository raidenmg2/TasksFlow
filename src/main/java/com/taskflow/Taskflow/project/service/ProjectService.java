package com.taskflow.Taskflow.project.service;

import com.taskflow.Taskflow.project.entity.Project;
import com.taskflow.Taskflow.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project update(UUID id, Project project) {
        Project existingProject = findById(id);

        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStatus(project.getStatus());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setDueDate(project.getDueDate());

        return projectRepository.save(existingProject);
    }

    public void delete(UUID id) {
        Project project = findById(id);
        projectRepository.delete(project);
    }
}
package com.taskflow.Taskflow.project.controller;


import com.taskflow.Taskflow.project.dto.CreateProjectRequest;
import com.taskflow.Taskflow.project.dto.ProjectResponse;
import com.taskflow.Taskflow.project.dto.UpdateProjectRequest;
import com.taskflow.Taskflow.project.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> findAll(){
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectResponse getById(@PathVariable UUID id){
        return projectService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @Valid @RequestBody CreateProjectRequest request) {
        ProjectResponse response = projectService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody UpdateProjectRequest request) {

        ProjectResponse response = projectService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id){
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }




}

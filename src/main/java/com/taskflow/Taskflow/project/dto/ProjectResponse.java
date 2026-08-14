package com.taskflow.Taskflow.project.dto;

import com.taskflow.Taskflow.project.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private UUID id;

    private String name;

    private String description;

    private ProjectStatus status;

    private LocalDate startDate;

    private LocalDate dueDate;

    private UUID ownerId;
}
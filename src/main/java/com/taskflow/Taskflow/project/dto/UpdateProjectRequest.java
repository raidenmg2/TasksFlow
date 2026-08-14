package com.taskflow.Taskflow.project.dto;

import com.taskflow.Taskflow.project.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProjectRequest {

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotNull(message = "El estado del proyecto es obligatorio")
    private ProjectStatus status;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    private LocalDate dueDate;
}
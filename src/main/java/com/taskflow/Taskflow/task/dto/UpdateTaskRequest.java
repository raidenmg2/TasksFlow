package com.taskflow.Taskflow.task.dto;

import com.taskflow.Taskflow.task.entity.Priority;
import com.taskflow.Taskflow.task.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskRequest {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150, message = "El título no puede superar los 150 caracteres")
    private String title;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotNull(message = "La prioridad es obligatoria")
    private Priority priority;

    @NotNull(message = "El estado es obligatorio")
    private TaskStatus status;

    private LocalDate dueDate;

    @NotNull(message = "El proyecto es obligatorio")
    private UUID projectId;

    @NotNull(message = "El usuario asignado es obligatorio")
    private UUID assignedUserId;
}
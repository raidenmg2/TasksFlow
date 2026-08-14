package com.taskflow.Taskflow.task.dto;

import com.taskflow.Taskflow.task.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskStatusRequest {

    @NotNull(message = "El estado de la tarea es obligatorio")
    private TaskStatus status;
}
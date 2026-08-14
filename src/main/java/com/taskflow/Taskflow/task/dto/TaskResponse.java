package com.taskflow.Taskflow.task.dto;

import com.taskflow.Taskflow.task.entity.Priority;
import com.taskflow.Taskflow.task.entity.TaskStatus;
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
public class TaskResponse {

    private UUID id;

    private String title;

    private String description;

    private Priority priority;

    private TaskStatus status;

    private LocalDate dueDate;

    private UUID projectId;

    private UUID assignedUserId;
}
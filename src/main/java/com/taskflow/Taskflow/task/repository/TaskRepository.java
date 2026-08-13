package com.taskflow.Taskflow.task.repository;

import com.taskflow.Taskflow.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}

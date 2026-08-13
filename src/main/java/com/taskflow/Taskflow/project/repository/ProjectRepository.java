package com.taskflow.Taskflow.project.repository;

import com.taskflow.Taskflow.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}

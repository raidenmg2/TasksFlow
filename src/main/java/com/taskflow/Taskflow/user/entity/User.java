package com.taskflow.Taskflow.user.entity;



import com.taskflow.Taskflow.common.entity.AuditableEntity;
import com.taskflow.Taskflow.project.entity.Project;
import com.taskflow.Taskflow.task.entity.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.UUID;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(name="first_name", nullable=false, length=50)
    private String firstName;

    @Column(name="last_name", nullable=false, length=50)
    private String lastName;

    @Column(name="email", nullable=false,unique = true, length=255)
    private String email;

    @Column(nullable = false, length=255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length=20)
    private UserRole role;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "owner")
    private List<Project> projects;

    @OneToMany(mappedBy = "assignedUser")
    private List<Task> assignedTasks;


}

package com.taskflow.Taskflow.user.dto;

import com.taskflow.Taskflow.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private UserRole role;

    private Boolean active;
}
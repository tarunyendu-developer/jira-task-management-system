package com.taskmanager.dto;

import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDto {

    @NotBlank(message = "Task title is required")
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long assignedUserId;
    private Long projectId;
    private LocalDate dueDate;
}
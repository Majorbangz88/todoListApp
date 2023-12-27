package com.Joel.todolistapp.dtos.requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UpdateTaskRequest {

    private String id;
    private String username;
    private String taskName;
    private String NewTaskName;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private boolean isComplete;
    private LocalDateTime reminderDateTime;
    private String taskCategory;
}

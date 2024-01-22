package com.Joel.todolistapp.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTaskResponse {
    private String username;
    private String taskName;
    private String description;
    private String dueDate;
    private String priority;
    private boolean isComplete;
    private LocalDateTime reminderDateTime;
    private String taskCategory;
}

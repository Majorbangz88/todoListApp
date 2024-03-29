package com.Joel.todolistapp.dtos.requests;

import com.Joel.todolistapp.data.models.TaskCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTaskRequest {

    private String id;
    private String username;
    private String taskName;
    private String description;
    private String dueDate;
    private String priority;
    private boolean isComplete;
    private LocalDateTime reminderDateTime;
    private String taskCategory;

}
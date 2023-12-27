package com.Joel.todolistapp.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("Task")
public class Task {

    @Id
    private String id;
    private User username;
    private String taskName;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private Boolean isComplete;
    private LocalDateTime reminderDateTime;
    private String taskCategory;

    @Override
    public String toString() {
        return "Here's your task:" +
                "TaskName: " + taskName +
                "Description: " + description +
                "Due Date: " + dueDate +
                "Priority: " + priority +
                "Is Complete: " + isComplete +
                "Reminder Date & Time: " + reminderDateTime +
                "Task Category: " + taskCategory;
    }
}

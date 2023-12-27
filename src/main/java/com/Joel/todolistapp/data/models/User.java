package com.Joel.todolistapp.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("User")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isLocked;
    private List<Task> tasks;
    private List<TaskCategory> category;
}

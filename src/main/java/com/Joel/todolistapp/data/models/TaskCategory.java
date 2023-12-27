package com.Joel.todolistapp.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("TaskCategory")
public class TaskCategory {

    @Id
    private String id;
    private String categoryName;
    private List<Task> task;

}

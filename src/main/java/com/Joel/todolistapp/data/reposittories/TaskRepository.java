package com.Joel.todolistapp.data.reposittories;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findTaskByTaskName(String taskName);

    Optional<Task> findByUsernameAndTaskName(User username, String taskName);

    Optional<Task> findByTaskName(String taskName);
}

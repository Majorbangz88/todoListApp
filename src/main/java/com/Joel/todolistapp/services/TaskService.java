package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.SetReminderRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;

import java.util.Optional;

public interface TaskService {

    Task findTaskBy(String senderUsername, String taskName);

    void saveTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findTaskByTaskName(String taskName);

    void deleteAll();

    long count();

    void save(CreateTaskRequest createTaskRequest);

    void updateTask(UpdateTaskRequest updateTaskRequest);

    void setReminder(SetReminderRequest setReminderRequest);
}

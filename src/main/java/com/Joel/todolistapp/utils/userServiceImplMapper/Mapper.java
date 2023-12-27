package com.Joel.todolistapp.utils.userServiceImplMapper;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.RegisterUserRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;

import java.util.Optional;

public class Mapper {

    public static User map(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        user.setLocked(true);
        return user;
    }

    public static void map(CreateTaskRequest createTaskrequest, Task task) {
        task.setTaskName(createTaskrequest.getTaskName());
        task.setDescription(createTaskrequest.getDescription());
        task.setDueDate(createTaskrequest.getDueDate());
        task.setPriority(createTaskrequest.getPriority());
        task.setIsComplete(createTaskrequest.isComplete());
        task.setReminderDateTime(createTaskrequest.getReminderDateTime());
        task.setTaskCategory(createTaskrequest.getTaskCategory());
    }

    public static Task map(UpdateTaskRequest updateTaskrequest, Optional<Task> task) {
        Task newTask = task.get();
        newTask.setTaskName(updateTaskrequest.getNewTaskName());
        newTask.setDescription(updateTaskrequest.getDescription());
        newTask.setDueDate(updateTaskrequest.getDueDate());
        newTask.setPriority(updateTaskrequest.getPriority());
        newTask.setIsComplete(updateTaskrequest.isComplete());
        newTask.setReminderDateTime(updateTaskrequest.getReminderDateTime());
        newTask.setTaskCategory(updateTaskrequest.getTaskCategory());
        return newTask;
    }

    public static Task map(User foundUser, Task foundTask) {
        Task newTask = new Task();
        newTask.setUsername(foundUser);
        newTask.setTaskName(foundTask.getTaskName());
        newTask.setDescription(foundTask.getDescription());
        newTask.setDueDate(foundTask.getDueDate());
        newTask.setPriority(foundTask.getPriority());
        newTask.setIsComplete(foundTask.getIsComplete());
        newTask.setReminderDateTime(foundTask.getReminderDateTime());
        newTask.setTaskCategory(foundTask.getTaskCategory());
        return newTask;
    }
}

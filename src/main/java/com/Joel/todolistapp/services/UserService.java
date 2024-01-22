package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.*;
import com.Joel.todolistapp.dtos.responses.CreateTaskResponse;

import java.util.Optional;

public interface UserService {
//    NotificationResponse sendNotification(NotificationRequest notificationRequest);

    User register(RegisterUserRequest registerUserRequest);


    User logout(String username);

    User unlock(UserLoginRequest userLoginRequest);

    CreateTaskResponse createTask(CreateTaskRequest createTaskrequest);

    User findUserBy(String username);

    Task updateTask(UpdateTaskRequest updateTaskRequest);

    void deleteTask(CreateTaskRequest createTaskRequest2);


    Task shareTask(ShareTaskRequest shareTaskRequest);

    Optional<User> findUserByUsernameForTask(String username);

    String getUsername();
}

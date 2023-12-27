package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.RegisterUserRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;
import com.Joel.todolistapp.dtos.requests.UserLoginRequest;
import com.Joel.todolistapp.dtos.requests.ShareTaskRequest;

import java.util.Optional;

public interface UserService {
    void register(RegisterUserRequest registerUserRequest);


    User isLocked();

    User isUnlocked(UserLoginRequest userLoginRequest);

    void createTask(CreateTaskRequest createTaskrequest);

    User findUserBy(String username);

    void updateTask(UpdateTaskRequest updateTaskRequest);

    void deleteTask(CreateTaskRequest createTaskRequest2);


    void shareTask(ShareTaskRequest shareTaskRequest);

    Optional<User> findUserByUsernameForTask(String username);

    String getUsername();
}

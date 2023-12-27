package com.Joel.todolistapp.controllers;

import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.*;
import com.Joel.todolistapp.services.TaskService;
import com.Joel.todolistapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/todolistApp")
public class TodoListController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/api/registerUser")
    public String register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            userService.register(registerUserRequest);
            return "Registration successful";
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }

    @PostMapping(path = "/api/userLogin")
    public String login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            userService.isUnlocked(userLoginRequest);
            return "Login successful";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @PostMapping(path = "/api/userLogout")
    public String logout() {
        userService.isLocked();
        return "Your details are secure!";
    }

    @PostMapping(path = "/api/userCreateTask")
    public String createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        try {
            userService.createTask(createTaskRequest);
            return "Task created successfully";
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }

    @PostMapping(path = "/api/userUpdateTask")
    public String updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        try {
            userService.updateTask(updateTaskRequest);
            return "Task updated!";
        } catch(Exception ex) {
           return ex.getMessage();
        }
    }

    @PostMapping(path = "/api/userDeleteTask")
    public String deleteTask(@RequestBody CreateTaskRequest createTaskRequest) {
        try {
            userService.deleteTask(createTaskRequest);
            return "Task deleted!";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @PostMapping(path = "/api/userShareTask")
    public String shareTask(@RequestBody ShareTaskRequest shareTaskRequest) {
        try {
            userService.shareTask(shareTaskRequest);
            return "Task shared successfully!";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @GetMapping(path = "/api/userFindTask/{taskName}")
    public String findTask(@PathVariable String taskName) {
        try {
            taskService.findTaskByTaskName(taskName);
            return "Task found!";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}

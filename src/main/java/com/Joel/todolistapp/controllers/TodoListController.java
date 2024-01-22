package com.Joel.todolistapp.controllers;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.*;
import com.Joel.todolistapp.dtos.responses.CreateTaskResponse;
import com.Joel.todolistapp.services.TaskService;
import com.Joel.todolistapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todolist")
public class TodoListController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/api/registration")
    public User register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            User registeredUser = userService.register(registerUserRequest);
            return registeredUser;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @PostMapping(path = "/api/login")
    public User login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            User loggedInUser = userService.unlock(userLoginRequest);
            return loggedInUser;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @PostMapping(path = "/api/Logout")
    public User logout(@RequestBody String username) {
        try {
            User user = userService.logout(username);
            return user;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @PostMapping(path = "/api/tasks")
    public CreateTaskResponse createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        try {
            CreateTaskResponse response = userService.createTask(createTaskRequest);
            return response;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @PatchMapping(path = "/api/updates")
    public Task updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        try {
            Task updatedTask = userService.updateTask(updateTaskRequest);
            return updatedTask;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @DeleteMapping(path = "/api/delete")
    public String deleteTask(@RequestBody CreateTaskRequest createTaskRequest) {
        try {
            userService.deleteTask(createTaskRequest);
            return "Task deleted!";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @DeleteMapping(path = "api/clear")
    public void deleteAllTasks() {
        taskService.deleteAll();
    }

    @PostMapping(path = "/api/shares")
    public Task shareTask(@RequestBody ShareTaskRequest shareTaskRequest) {
        try {
            Task task = userService.shareTask(shareTaskRequest);
            return task;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @GetMapping(path = "/api/find/{taskName}")
    public Optional<Task> findTask(@PathVariable String taskName) {
        try {
            Optional<Task> foundTask = taskService.findTaskByTaskName(taskName);
            return foundTask;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    @GetMapping(path = "api/findAll")
    public List<Task> findAllTasks() {
        try {
            List<Task> allTasks = taskService.returnAllTasks();
            return allTasks;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

}

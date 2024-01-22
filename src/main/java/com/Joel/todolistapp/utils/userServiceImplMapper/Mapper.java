package com.Joel.todolistapp.utils.userServiceImplMapper;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.RegisterUserRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;
import com.Joel.todolistapp.dtos.responses.CreateTaskResponse;
import com.Joel.todolistapp.dtos.responses.ShareTaskResponse;

import java.util.Optional;

public class Mapper {

    public static User map(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        user.setLocked(true);
        return user;
    }

    public static CreateTaskResponse map(CreateTaskRequest createTaskrequest, Task task) {
        getCreateTask(createTaskrequest, task);

        CreateTaskResponse response = getCreateTaskResponse(createTaskrequest);

        return response;
    }

    public static void getCreateTask(CreateTaskRequest createTaskrequest, Task task) {
        User taskCreator = new User();
        taskCreator.setUsername(createTaskrequest.getUsername());

        task.setUsername(taskCreator);
        task.setTaskName(createTaskrequest.getTaskName());
        task.setDescription(createTaskrequest.getDescription());
        task.setDueDate(createTaskrequest.getDueDate());
        task.setPriority(createTaskrequest.getPriority());
        task.setIsComplete(createTaskrequest.isComplete());
        task.setReminderDateTime(createTaskrequest.getReminderDateTime());
        task.setTaskCategory(createTaskrequest.getTaskCategory());
    }

    private static CreateTaskResponse getCreateTaskResponse(CreateTaskRequest createTaskrequest) {
        CreateTaskResponse response = new CreateTaskResponse();
        response.setTaskName(createTaskrequest.getUsername());
        response.setDescription(createTaskrequest.getDescription());
        response.setDueDate(createTaskrequest.getDueDate());
        response.setPriority(createTaskrequest.getPriority());
        response.setComplete(createTaskrequest.isComplete());
        response.setReminderDateTime(createTaskrequest.getReminderDateTime());
        response.setTaskCategory(createTaskrequest.getTaskCategory());
        return response;
    }

    public static Task map(UpdateTaskRequest updateTaskrequest, Task newTask) {
        newTask.setTaskName(updateTaskrequest.getNewTaskName());
        newTask.setDescription(updateTaskrequest.getDescription());
        newTask.setDueDate(String.valueOf(updateTaskrequest.getDueDate()));
        newTask.setPriority(updateTaskrequest.getPriority());
        newTask.setIsComplete(updateTaskrequest.isComplete());
        newTask.setReminderDateTime(updateTaskrequest.getReminderDateTime());
        newTask.setTaskCategory(updateTaskrequest.getTaskCategory());
        return newTask;
    }

    public static Task map(User foundUser, Task foundTask) {
        User receiver = new User();
        receiver.setUsername(foundUser.getUsername());
//        receiver.setPassword(receiver.getPassword());

        Task newTask = new Task();
        newTask.setUsername(receiver);
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

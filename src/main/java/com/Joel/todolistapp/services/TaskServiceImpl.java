package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.data.reposittories.TaskRepository;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.NotificationRequest;
import com.Joel.todolistapp.dtos.requests.SetReminderRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;
import com.Joel.todolistapp.dtos.responses.CreateTaskResponse;
import com.Joel.todolistapp.exceptions.TaskNotFoundException;
import com.Joel.todolistapp.exceptions.TasknameOrUsernameNotFound;
import com.Joel.todolistapp.exceptions.UniqueUserException;
import com.Joel.todolistapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.Joel.todolistapp.utils.userServiceImplMapper.Mapper.map;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;
    @Autowired
    NotificationService notificationService;


    @Override
    public Task findTaskBy(String senderUsername, String taskName) {
        User foundUser = userService.findUserBy(senderUsername);
        Optional<Task> optionalTask = taskRepository.findByTaskName(taskName);
        if (optionalTask.isEmpty()) throw new TaskNotFoundException("Task or user not found!");
        Task foundTask = optionalTask.get();
        if (foundUser.getUsername().equals(senderUsername) && foundTask.getTaskName().equals(taskName))
            return optionalTask.get();
        return null;
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Optional<Task> findTaskByTaskName(String taskName) {
        return taskRepository.findTaskByTaskName(taskName);
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @Override
    public long count() {
        return taskRepository.count();
    }

    @Override
    public CreateTaskResponse save(CreateTaskRequest createTaskRequest) {
        Task newTask = new Task();
//        newTask.setUsername(userService.findUserBy(createTaskRequest.getUsername()));
        CreateTaskResponse response = map(createTaskRequest, newTask);
        taskRepository.save(newTask);

        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setTitle(createTaskRequest.getTaskName());
        notificationRequest.setContent(notificationRequest.getContent());
        notificationRequest.setDateTime(notificationRequest.getDateTime());

        notificationService.sendNotification(notificationRequest);

        return response;
    }

    @Override
    public Task updateTask(UpdateTaskRequest updateTaskRequest) {
        Task task = findTaskBy(updateTaskRequest.getUsername(), updateTaskRequest.getTaskName());
        Task newTask = map(updateTaskRequest, task);
//        taskRepository.delete(foundTask.get());
        taskRepository.save(newTask);

        return newTask;
    }

    @Override
    public void setReminder(SetReminderRequest setReminderRequest) {

    }

    @Override
    public List<Task> returnAllTasks() {
        return taskRepository.findAll();
    }

    private User findBy(String username) {
        Optional<User> foundUser = userService.findUserByUsernameForTask(username);
        if (foundUser.isEmpty()) throw new UserNotFoundException("User not found!");
        return foundUser.get();
    }

}

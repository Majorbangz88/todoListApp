package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.data.reposittories.TaskRepository;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.SetReminderRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;
import com.Joel.todolistapp.exceptions.TaskNotFoundException;
import com.Joel.todolistapp.exceptions.TasknameOrUsernameNotFound;
import com.Joel.todolistapp.exceptions.UniqueUserException;
import com.Joel.todolistapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.Joel.todolistapp.utils.userServiceImplMapper.Mapper.map;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;


    @Override
    public Task findTaskBy(String senderUsername, String taskName) {
        Optional<Task> foundTask = taskRepository.findTaskByUsernameAndTaskName(userService.findUserBy(senderUsername), taskName);
        if (foundTask.isEmpty()) throw new TasknameOrUsernameNotFound("Task name or username not found!");
        if (foundTask.get().getTaskName().equals(taskName))
            return foundTask.get();
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
    public void save(CreateTaskRequest createTaskRequest) {
        Task newTask = new Task();
        newTask.setUsername(userService.findUserBy(createTaskRequest.getUsername()));
        map(createTaskRequest, newTask);
        taskRepository.save(newTask);
    }

    @Override
    public void updateTask(UpdateTaskRequest updateTaskRequest) {
        Optional<Task> foundTask = taskRepository.findTaskByUsernameAndTaskName(
                findBy(updateTaskRequest.getUsername()), updateTaskRequest.getTaskName());
        if (foundTask.isEmpty()) throw new TaskNotFoundException("Task not found!");
        Task newTask = map(updateTaskRequest, foundTask);
        taskRepository.delete(foundTask.get());
        taskRepository.save(newTask);
    }

    @Override
    public void setReminder(SetReminderRequest setReminderRequest) {

    }

    private User findBy(String username) {
        Optional<User> foundUser = userService.findUserByUsernameForTask(username);
        if (foundUser.isEmpty()) throw new UserNotFoundException("User not found!");
        return foundUser.get();
    }

}

package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.data.reposittories.UserRepository;
import com.Joel.todolistapp.dtos.requests.*;
import com.Joel.todolistapp.dtos.responses.CreateTaskResponse;
import com.Joel.todolistapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.Joel.todolistapp.utils.userServiceImplMapper.Mapper.map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private TaskService taskService;


//    @Override
//    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
//        return notificationService.sendNotification(notificationRequest);
//    }

    @Override
    public User register(RegisterUserRequest registerUserRequest) {
        findUserBy(registerUserRequest);
        User user = map(registerUserRequest);
        userRepository.save(user);

        return user;
    }

    @Override
    public User logout(String username) {
        User foundUser = findUserBy(username);
        foundUser.setLocked(true);
        userRepository.save(foundUser);

        return foundUser;
    }

    public User unlock(UserLoginRequest userLoginRequest) {
        Optional<User> user = userRepository.findFirstUserByUsername(userLoginRequest.getUsername());
        if(user.isEmpty()) throw new UserNotFoundException("User not found");
        if (user.get().getUsername().equals(userLoginRequest.getUsername()) &&
                user.get().getPassword().equals(userLoginRequest.getPassword()))
            user.get().setLocked(false);
        else throw new IncorrectUsernameOrPasswordException("Username or password is incorrect");
        userRepository.save(user.get());

        return user.get();
    }

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest createTaskrequest) {
        CreateTaskResponse response = taskService.save(createTaskrequest);
        return response;
    }

    @Override
    public User findUserBy(String username) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        if (foundUser.isEmpty()) throw new UserNotFoundException("User not found!");
        return foundUser.get();
    }
    @Override
    public Task updateTask(UpdateTaskRequest updateTaskrequest) {
        Task updatedTask =  taskService.updateTask(updateTaskrequest);
        return updatedTask;
    }

    @Override
    public void deleteTask(CreateTaskRequest createTaskRequest2) {
        Optional<Task> task = taskService.findTaskByTaskName(createTaskRequest2.getTaskName());
        if (task.isEmpty()) throw new TaskNotFoundException("Task not found!");
        taskService.deleteTask(task.get());
    }

    @Override
    public Task shareTask(ShareTaskRequest shareTaskRequest) {
        Task foundTask = taskService.findTaskBy(shareTaskRequest.getSenderUsername(), shareTaskRequest.getTaskName());
        User foundUser = findUserBy(shareTaskRequest.getReceiverUsername());
        Task newTask = map(foundUser, foundTask);
        taskService.saveTask(newTask);

        return newTask;
    }

    @Override
    public Optional<User> findUserByUsernameForTask(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public String getUsername() {
        User user = new User();
        return user.getUsername();
    }


    private void findUserBy(RegisterUserRequest registerUserRequest) {
        Optional<User> user = userRepository.findUserByUsername(registerUserRequest.getUsername());
        if (user.isPresent())
            throw new UniqueUserException("Username exists already!!");
    }


}

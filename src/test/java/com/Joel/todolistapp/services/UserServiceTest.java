package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.User;
import com.Joel.todolistapp.data.reposittories.UserRepository;
import com.Joel.todolistapp.dtos.requests.*;
import com.Joel.todolistapp.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @BeforeEach
    public void delete() {
        userRepository.deleteAll();
        taskService.deleteAll();
    }

    @Test
    public void testThatTodoListIsLocked() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        User user = userService.isLocked();
        assertTrue(user.isLocked());
    }

    @Test
    public void testThatNewUserCanRegister() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void testThatUsernameIsUniqueAndThrowsExceptionIfNot() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("Username");
        registerUserRequest1.setPassword("password");
        assertThrows(UniqueUserException.class, ()-> userService.register(registerUserRequest1));
    }


    @Test
    public void testThatWhenUserIsRegistered_UserCanLogIn_AndAppIsUnlocked() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());
    }

    @Test
    public void testThatExceptionIsThrownIfUsernameOrPasswordIsIncorrect() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("passwod");
        assertThrows(IncorrectUsernameOrPasswordException.class, ()-> userService.isUnlocked(userLoginRequest));
    }

    @Test
    public void testThatUserCanCreateTask() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = new CreateTaskRequest();
        createTaskrequest.setUsername("Username");
        createTaskrequest.setTaskName("Write my todolist app");
        createTaskrequest.setDescription("It is important i write mt todolist app!");
        createTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskrequest.setPriority("Very important");
        createTaskrequest.setComplete(false);
        createTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));
    }

    @Test
    public void testThatTaskCanBeUpdated() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = new CreateTaskRequest();
        createTaskrequest.setUsername("Username");
        createTaskrequest.setTaskName("Write my todolist app");
        createTaskrequest.setDescription("It is important i write mt todolist app!");
        createTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskrequest.setPriority("Very important");
        createTaskrequest.setComplete(false);
        createTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        UpdateTaskRequest updateTaskrequest = new UpdateTaskRequest();
        updateTaskrequest.setUsername("Username");
        updateTaskrequest.setTaskName("Write my todolist app");
        updateTaskrequest.setNewTaskName("Write my todolist app in good time");
        updateTaskrequest.setDescription("Make hay while the sun shines");
        updateTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 10));
        updateTaskrequest.setPriority("Extremely important");
        updateTaskrequest.setComplete(false);
        updateTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                9, 10, 30, 45));
        updateTaskrequest.setTaskCategory("Self development");
        userService.updateTask(updateTaskrequest);
        assertThat(taskService.count(), is(1L));
    }

    @Test
    public void testThatExceptionIsThrownWhenTaskIsNotFoundAndUserWantsToUpdateTask() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = new CreateTaskRequest();
        createTaskrequest.setUsername("Username");
        createTaskrequest.setTaskName("Write my todolist app");
        createTaskrequest.setDescription("It is important i write mt todolist app!");
        createTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskrequest.setPriority("Very important");
        createTaskrequest.setComplete(false);
        createTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        UpdateTaskRequest updateTaskrequest = new UpdateTaskRequest();
        updateTaskrequest.setUsername("Username");
        updateTaskrequest.setTaskName("Write my todolist apps");
        updateTaskrequest.setNewTaskName("Write my todolist app in good time");
        updateTaskrequest.setDescription("Make hay while the sun shines");
        updateTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 10));
        updateTaskrequest.setPriority("Extremely important");
        updateTaskrequest.setComplete(false);
        updateTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                9, 10, 30, 45));
        updateTaskrequest.setTaskCategory("Self development");
        assertThrows(TaskNotFoundException.class, ()-> userService.updateTask(updateTaskrequest));
    }

    @Test
    public void testThatUserCanDeleteTask() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = new CreateTaskRequest();
        createTaskrequest.setUsername("Username");
        createTaskrequest.setTaskName("Write my todolist app");
        createTaskrequest.setDescription("It is important i write mt todolist app!");
        createTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskrequest.setPriority("Very important");
        createTaskrequest.setComplete(false);
        createTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        CreateTaskRequest createTaskRequest2 = new CreateTaskRequest();
        createTaskRequest2.setUsername("Username");
        createTaskRequest2.setTaskName("Daily improvement");
        createTaskRequest2.setDescription("Read 10 pages of book tomorrow morning");
        createTaskRequest2.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskRequest2.setPriority("Very important");
        createTaskRequest2.setComplete(false);
        createTaskRequest2.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskRequest2.setTaskCategory("Self development");
        userService.createTask(createTaskRequest2);
        assertThat(taskService.count(), is(2L));

        userService.deleteTask(createTaskRequest2);
        assertThat(taskService.count(), is(1L));
    }

    @Test
    public void testThatUserCanShareTask() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("majorGeneral");
        registerUserRequest1.setPassword("password");
        userService.register(registerUserRequest1);
        assertThat(userRepository.count(), is(2L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = new CreateTaskRequest();
        createTaskrequest.setUsername("Username");
        createTaskrequest.setTaskName("Write my todolist app");
        createTaskrequest.setDescription("It is important i write mt todolist app!");
        createTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskrequest.setPriority("Very important");
        createTaskrequest.setComplete(false);
        createTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        ShareTaskRequest shareTaskRequest = new ShareTaskRequest();
        shareTaskRequest.setSenderUsername("Username");
        shareTaskRequest.setTaskName("Write my todolist app");
        shareTaskRequest.setReceiverUsername("majorGeneral");
        userService.shareTask(shareTaskRequest);
        assertThat(userRepository.count(), is(2L));
        assertThat(taskService.count(), is(2L));
    }

    @Test
    public void testThatExceptionIsThrownIfUserIsNotFound() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("majorGeneral");
        registerUserRequest1.setPassword("password");
        userService.register(registerUserRequest1);
        assertThat(userRepository.count(), is(2L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.isUnlocked(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = new CreateTaskRequest();
        createTaskrequest.setUsername("Username");
        createTaskrequest.setTaskName("Write my todolist app");
        createTaskrequest.setDescription("It is important i write mt todolist app!");
        createTaskrequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 9));
        createTaskrequest.setPriority("Very important");
        createTaskrequest.setComplete(false);
        createTaskrequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        ShareTaskRequest shareTaskRequest = new ShareTaskRequest();
        shareTaskRequest.setSenderUsername("Usernames");
        shareTaskRequest.setTaskName("Write my todolist app");
        shareTaskRequest.setReceiverUsername("majorGenerals");
        assertThrows(UserNotFoundException.class, ()-> userService.shareTask(shareTaskRequest));
    }
}
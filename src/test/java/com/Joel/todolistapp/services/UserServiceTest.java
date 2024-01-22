package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Task;
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
import java.util.List;
import java.util.Optional;

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

    @Autowired
    NotificationService notificationService;

    @BeforeEach
    public void delete() {
        userRepository.deleteAll();
        taskService.deleteAll();
        notificationService.deleteAll();
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());
    }

    @Test
    public void testThatTodoListIsLocked_WhenUserLogsOut() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("Username");
        userLoginRequest.setPassword("password");
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        User loggedOut = userService.logout(registerUserRequest.getUsername());
        assertTrue(loggedOut.isLocked());
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
        assertThrows(IncorrectUsernameOrPasswordException.class, ()-> userService.unlock(userLoginRequest));
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

//        NotificationRequest notificationRequest = new NotificationRequest();
//        notificationRequest.setTitle(createTaskrequest.getTaskName());
//        notificationRequest.setContent(createTaskrequest.getDescription());
//        notificationRequest.setDateTime(createTaskrequest.getReminderDateTime());
//
//        NotificationResponse notificationResponse = notificationService.sendNotification(notificationRequest);
//        assertNotNull(notificationResponse);
//        assertThat(notificationService.count(), is(1L));
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        UpdateTaskRequest updateTaskrequest = new UpdateTaskRequest();
        updateTaskrequest.setUsername(createTaskrequest.getUsername());
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        CreateTaskRequest createTaskRequest2 = getCreateTaskRequest("Daily improvement", "Read 10 pages of book tomorrow morning");
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        ShareTaskRequest shareTaskRequest = new ShareTaskRequest();
        shareTaskRequest.setSenderUsername(createTaskrequest.getUsername());
        shareTaskRequest.setTaskName(createTaskrequest.getTaskName());
        shareTaskRequest.setReceiverUsername(registerUserRequest1.getUsername());

        userService.shareTask(shareTaskRequest);
        assertThat(userRepository.count(), is(2L));
        assertThat(taskService.count(), is(2L));
    }

    @Test
    public void testThatExceptionIsThrownIfSenderOrReceiverIsNotFound() {
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);
        assertThat(taskService.count(), is(1L));

        ShareTaskRequest shareTaskRequest = new ShareTaskRequest();
        shareTaskRequest.setSenderUsername("Usernames");
        shareTaskRequest.setTaskName("Write my todolist app");
        shareTaskRequest.setReceiverUsername("majorGenerals");
        assertThrows(UserNotFoundException.class, ()-> userService.shareTask(shareTaskRequest));
    }

    @Test
    public void testThatUserCanFindListOfTasks() {
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskrequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskrequest);

        CreateTaskRequest createTaskRequest2 = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskrequest.setTaskCategory("Self development");
        userService.createTask(createTaskRequest2);
        assertThat(taskService.count(), is(2L));

        List<Task> allTasks = taskService.returnAllTasks();
        assertNotNull(allTasks);
    }

    private static CreateTaskRequest getCreateTaskRequest(String Write_my_todolist_app, String description) {
        CreateTaskRequest createTaskRequest2 = new CreateTaskRequest();
        createTaskRequest2.setUsername("Username");
        createTaskRequest2.setTaskName(Write_my_todolist_app);
        createTaskRequest2.setDescription(description);
        createTaskRequest2.setDueDate("2024/02/12");
        createTaskRequest2.setPriority("Very important");
        createTaskRequest2.setComplete(false);
        createTaskRequest2.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                8, 10, 30, 45));
        return createTaskRequest2;
    }

    @Test
    public void testThatTaskCanBeFoundByTaskName() {
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
        User user = userService.unlock(userLoginRequest);
        assertFalse(user.isLocked());

        CreateTaskRequest createTaskRequest = getCreateTaskRequest("Write my todolist app", "It is important i write mt todolist app!");
        createTaskRequest.setTaskCategory("Self development");
        userService.createTask(createTaskRequest);

        CreateTaskRequest createTaskRequest2 = getCreateTaskRequest("Fix all your bugs man", "It is important you fix your code bugs bro!");
        createTaskRequest2.setTaskCategory("Self development");
        userService.createTask(createTaskRequest2);

        Optional<Task> foundTask = taskService.findTaskByTaskName(createTaskRequest2.getTaskName());

        assertNotNull(foundTask);
    }
}
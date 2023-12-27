//package com.Joel.todolistapp.controllers;
//
//import com.Joel.todolistapp.data.models.User;
//import com.Joel.todolistapp.dtos.requests.RegisterUserRequest;
//import com.Joel.todolistapp.dtos.requests.UserLoginRequest;
//import com.Joel.todolistapp.services.TaskService;
//import com.Joel.todolistapp.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class TodoListControllerTest {
//
//    private TodoListController todoListController;
//    private RegisterUserRequest registerUserRequest;
//    private UserLoginRequest userLoginRequest;
//    @BeforeEach
//    public void startWithThis() {
//        todoListController = new TodoListController();
//        registerUserRequest = new RegisterUserRequest();
//        userLoginRequest = new UserLoginRequest();
//
//        registerUserRequest.setUsername("Username");
//        registerUserRequest.setPassword("password");
//
//        userLoginRequest.setUsername("Username");
//        userLoginRequest.setPassword("password");
//    }
//
//    @Test
//    public void testThatRegisterMethodWorks() {
//        String newUser = todoListController.register(registerUserRequest);
//        assertEquals("Registration successful", newUser);
//    }

//    @Test
//    public void testThatLoginMethodWorks() {
//        String newUser = todoListController.register(registerUserRequest);
//        assertEquals("Registration successful", newUser);
//
//
//        assertTrue(todoListController.isLocked());
//    }
//}
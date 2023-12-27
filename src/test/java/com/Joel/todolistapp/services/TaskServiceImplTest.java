package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.reposittories.TaskRepository;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.dtos.requests.SetReminderRequest;
import com.Joel.todolistapp.dtos.requests.UpdateTaskRequest;
import com.Joel.todolistapp.exceptions.TaskNotFoundException;
import com.Joel.todolistapp.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void tearDown() {
        taskRepository.deleteAll();;
    }
    @Autowired
    private TaskService taskService;

    @Test
    public void testThatTaskCanBeCreated() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setUsername("Username");
        createTaskRequest.setTaskName("Create task");
        createTaskRequest.setDescription("Create some task");
        createTaskRequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 23));
        createTaskRequest.setPriority("Very important");
        createTaskRequest.setComplete(false);
        createTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                23, 10, 30, 45));
        createTaskRequest.setTaskCategory("Read a book");
        taskService.save(createTaskRequest);
        assertThat(taskRepository.count(), is(1L));
    }

    @Test
    public void testThatTaskCanBeEdited() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setUsername("Username");
        createTaskRequest.setTaskName("Create task");
        createTaskRequest.setDescription("Create some task");
        createTaskRequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 23));
        createTaskRequest.setPriority("Very important");
        createTaskRequest.setComplete(false);
        createTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                23, 10, 30, 45));
        createTaskRequest.setTaskCategory("Read a book");
        taskService.save(createTaskRequest);
        assertThat(taskRepository.count(), is(1L));

        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setUsername("Username");
        updateTaskRequest.setTaskName("Create task");
        updateTaskRequest.setDescription("Create some more task");
        updateTaskRequest.setDueDate(LocalDate.of(2024, Month.NOVEMBER, 13));
        updateTaskRequest.setPriority("Very important");
        updateTaskRequest.setComplete(false);
        updateTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                23, 10, 30, 45));
        updateTaskRequest.setTaskCategory("Read 10 books");
        taskService.updateTask(updateTaskRequest);
        assertThat(taskRepository.count(), is(1L));
    }

    @Test
    public void testThatExceptionIsThrownWhenUserWantsToUpdateTask_WhileTaskNameIsIncorrect() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setUsername("Username");
        createTaskRequest.setTaskName("Create task");
        createTaskRequest.setDescription("Create some task");
        createTaskRequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 23));
        createTaskRequest.setPriority("Very important");
        createTaskRequest.setComplete(false);
        createTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                23, 10, 30, 45));
        createTaskRequest.setTaskCategory("Read a book");
        taskService.save(createTaskRequest);
        assertThat(taskRepository.count(), is(1L));

        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setUsername("Username");
        updateTaskRequest.setTaskName("Create new task");
        updateTaskRequest.setDescription("Create some more task");
        updateTaskRequest.setDueDate(LocalDate.of(2024, Month.NOVEMBER, 13));
        updateTaskRequest.setPriority("Very important");
        updateTaskRequest.setComplete(false);
        updateTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                23, 10, 30, 45));
        updateTaskRequest.setTaskCategory("Read 10 books");
        assertThrows(TaskNotFoundException.class, ()-> taskService.updateTask(updateTaskRequest));
//        assertThrows(UserNotFoundException.class, ()-> taskService.updateTask(updateTaskRequest));
    }

    @Test
    public void testThatTaskCanBeMarkedComplete() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setUsername("Username");
        createTaskRequest.setTaskName("Create task");
        createTaskRequest.setDescription("Create some task");
        createTaskRequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 23));
        createTaskRequest.setPriority("Very important");
        createTaskRequest.setComplete(true);
        createTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
                23, 10, 30, 45));
        createTaskRequest.setTaskCategory("Read a book");
        taskService.save(createTaskRequest);
        assertTrue(createTaskRequest.isComplete());
    }

//    @Test
//    public void testThatUserCanSetReminderForTasks() {
//        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
//        createTaskRequest.setUsername("Username");
//        createTaskRequest.setTaskName("Create task");
//        createTaskRequest.setDescription("Create some task");
//        createTaskRequest.setDueDate(LocalDate.of(2023, Month.NOVEMBER, 23));
//        createTaskRequest.setPriority("Very important");
//        createTaskRequest.setComplete(false);
//        createTaskRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
//                23, 10, 30, 45));
//        createTaskRequest.setTaskCategory("Read a book");
//        taskService.save(createTaskRequest);
//        assertTrue(createTaskRequest.isComplete());
//
//        SetReminderRequest setReminderRequest = new SetReminderRequest();
//        setReminderRequest.setReminderDateTime(LocalDateTime.of(2023, Month.NOVEMBER,
//                23, 10, 30, 45));
//        taskService.setReminder(setReminderRequest);
//    }

}
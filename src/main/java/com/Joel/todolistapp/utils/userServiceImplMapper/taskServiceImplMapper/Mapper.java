package com.Joel.todolistapp.utils.userServiceImplMapper.taskServiceImplMapper;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.services.UserService;

public class Mapper {

    public void map(CreateTaskRequest createTaskRequest, Task newTask) {
        newTask.setTaskName(createTaskRequest.getTaskName());
        newTask.setDescription(createTaskRequest.getDescription());
        newTask.setDueDate(createTaskRequest.getDueDate());
        newTask.setPriority(createTaskRequest.getPriority());
        newTask.setIsComplete(createTaskRequest.isComplete());
        newTask.setReminderDateTime(createTaskRequest.getReminderDateTime());
        newTask.setTaskCategory(createTaskRequest.getTaskCategory());
    }
}

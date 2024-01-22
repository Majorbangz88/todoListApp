package com.Joel.todolistapp.utils.userServiceImplMapper.taskServiceImplMapper;

import com.Joel.todolistapp.data.models.Task;
import com.Joel.todolistapp.dtos.requests.CreateTaskRequest;
import com.Joel.todolistapp.services.UserService;

public class Mapper {

    public void map(CreateTaskRequest createTaskRequest, Task newTask) {
        com.Joel.todolistapp.utils.userServiceImplMapper.Mapper.getCreateTask(createTaskRequest, newTask);
    }
}

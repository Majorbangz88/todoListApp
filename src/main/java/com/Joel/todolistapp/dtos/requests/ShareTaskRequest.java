package com.Joel.todolistapp.dtos.requests;

import com.Joel.todolistapp.data.models.Task;
import lombok.Data;

@Data
public class ShareTaskRequest {
    private String senderUsername;
    private String taskName;
    private String receiverUsername;
}

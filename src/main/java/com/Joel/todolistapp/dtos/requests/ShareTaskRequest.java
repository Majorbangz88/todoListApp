package com.Joel.todolistapp.dtos.requests;

import lombok.Data;

@Data
public class ShareTaskRequest {
    private String senderUsername;
    private String taskName;
    private String receiverUsername;
}

package com.Joel.todolistapp.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationRequest {

    private String title;
    private String content;
    private LocalDateTime dateTime;
//    private boolean isRead;
}

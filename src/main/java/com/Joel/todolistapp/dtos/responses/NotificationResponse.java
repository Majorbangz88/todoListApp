package com.Joel.todolistapp.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponse {
    private String title;
    private String content;
    private LocalDateTime dateTime;
}

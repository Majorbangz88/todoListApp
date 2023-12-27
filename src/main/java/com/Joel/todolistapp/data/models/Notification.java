package com.Joel.todolistapp.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Data
@Document("Notification")
public class Notification {

    @Id
    private String id;
    private int NotificationId;
    private String content;
    private boolean isRead;
    private LocalDateTime dateTime;


}

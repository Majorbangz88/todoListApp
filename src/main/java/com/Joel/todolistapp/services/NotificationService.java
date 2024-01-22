package com.Joel.todolistapp.services;

import com.Joel.todolistapp.dtos.requests.NotificationRequest;
import com.Joel.todolistapp.dtos.responses.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest notificationRequest);

    Long count();

    void deleteAll();
}

package com.Joel.todolistapp.services;

import com.Joel.todolistapp.data.models.Notification;
import com.Joel.todolistapp.data.reposittories.NotificationRepository;
import com.Joel.todolistapp.dtos.requests.NotificationRequest;
import com.Joel.todolistapp.dtos.responses.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
        Notification notification = new Notification();
        notification.setTitle(notificationRequest.getTitle());
        notification.setContent(notificationRequest.getContent());
        notification.setDateTime(notificationRequest.getDateTime());
        notificationRepository.save(notification);

        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setTitle(notificationRequest.getTitle());
        notificationResponse.setContent(notificationRequest.getContent());
        notificationResponse.setDateTime(notificationRequest.getDateTime());

        return notificationResponse;
    }

    @Override
    public Long count() {
        return notificationRepository.count();
    }

    @Override
    public void deleteAll() {
        notificationRepository.deleteAll();;
    }
}

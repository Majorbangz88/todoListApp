package com.Joel.todolistapp.data.reposittories;

import com.Joel.todolistapp.data.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

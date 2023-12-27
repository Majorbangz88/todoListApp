package com.Joel.todolistapp.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SetReminderRequest {

    private LocalDateTime reminderDateTime;
}

package com.jxt.jxtbank.notification.entity;

import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.enums.NotificationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Notification {


    private Long id;

    private String subject;
    private String recipient;

    private String body;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private final LocalDateTime createdAt = LocalDateTime.now();
}

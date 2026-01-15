package com.jxt.jxtbank.notification.entity;

import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

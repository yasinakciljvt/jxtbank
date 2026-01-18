package com.jxt.jxtbank.notification.service;

import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.notification.dto.NotificationDTO;

public interface NotificationService {

    void sendEmail(NotificationDTO notificationDTO, User user);
}

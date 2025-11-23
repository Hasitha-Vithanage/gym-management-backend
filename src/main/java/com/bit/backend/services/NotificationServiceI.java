package com.bit.backend.services;

import com.bit.backend.dtos.NotificationDto;

import java.util.List;

public interface NotificationServiceI {

    NotificationDto addNotification(NotificationDto notificationDto);
    List<NotificationDto> getUserNotifications(long id);
    boolean changeNotificationStatus(String id);
}

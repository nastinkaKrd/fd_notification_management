package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    String sendMessageOnEmail(String email, String subject, String text);

    List<NotificationDto> getNotifications();

    void addNotification(NotificationDto notificationDto);
}

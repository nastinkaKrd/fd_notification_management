package com.nastinka_krd.fooddelivery.notifications.service;

import com.nastinka_krd.fooddelivery.notifications.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    String sendMessageOnEmail(String email, String subject, String text);

    List<NotificationDto> getNotifications();

    void addNotification(NotificationDto notificationDto);
}

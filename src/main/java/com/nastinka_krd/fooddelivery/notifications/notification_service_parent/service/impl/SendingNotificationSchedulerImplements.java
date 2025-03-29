package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.impl;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.User;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.repository.NotificationRepository;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.repository.UserRepository;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.NotificationService;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendingNotificationSchedulerImplements {
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 15 * * ?")
    public void sendNotification() {
        LocalDate localDate = LocalDate.now();
        List<Notification> notifications = notificationRepository.findAllByPlanningDate(localDate);
        if (!notifications.isEmpty()){
            List<User> users = userRepository.findAllByIsSendingNotificationAllowed(true);
            notifications.forEach(notification -> {
                users.forEach(
                        user -> {
                            notificationService.sendMessageOnEmail(user.getEmail(), notification.getTitle(), notification.getDescription());
                        }
                        );
                    }
            );
        }
    }
}

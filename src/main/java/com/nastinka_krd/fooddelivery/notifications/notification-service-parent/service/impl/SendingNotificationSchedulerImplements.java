package com.nastinka_krd.fooddelivery.notifications.service.impl;

import com.nastinka_krd.fooddelivery.notifications.domain.User;
import com.nastinka_krd.fooddelivery.notifications.repository.NotificationRepository;
import com.nastinka_krd.fooddelivery.notifications.repository.UserRepository;
import com.nastinka_krd.fooddelivery.notifications.service.NotificationService;
import com.nastinka_krd.fooddelivery.notifications.domain.Notification;
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

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendNotification() {
        LocalDate localDate = LocalDate.now();
        List<Notification> notifications = notificationRepository.findAllByPlanningDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
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

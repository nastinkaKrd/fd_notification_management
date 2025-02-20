package com.nastinka_krd.notificationmanagement.service.impl;

import com.nastinka_krd.notificationmanagement.domain.Notification;
import com.nastinka_krd.notificationmanagement.domain.User;
import com.nastinka_krd.notificationmanagement.repository.NotificationRepository;
import com.nastinka_krd.notificationmanagement.repository.UserRepository;
import com.nastinka_krd.notificationmanagement.service.NotificationService;
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

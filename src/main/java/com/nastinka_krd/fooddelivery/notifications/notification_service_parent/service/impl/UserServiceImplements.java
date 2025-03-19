package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.impl;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.User;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.UserRoles;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.repository.UserRepository;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.UserService;
import com.nastinka_krd.user_management.api.dto.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImplements implements UserService {
    private final UserRepository userRepository;


    @KafkaListener(topics = "user-saving", groupId = "notification-group",
            containerFactory = "userDataKafkaListenerContainerFactory")
    private void listenUserSavingEvent(UserData userDataForNotifications) {
        log.info("Received user data for notifications: {}", userDataForNotifications);
        try {
            if (userRepository.findByUsername(userDataForNotifications.getUsername()).isEmpty()){
                User user = User.builder()
                        .id(userDataForNotifications.getId())
                        .username(userDataForNotifications.getUsername())
                        .email(userDataForNotifications.getEmail())
                        .password(userDataForNotifications.getPassword())
                        .role(UserRoles.valueOf(userDataForNotifications.getRole()))
                        .isSendingNotificationAllowed(userDataForNotifications.getIsSendingNotificationAllowed()).build();
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateIsSendingNotificationAllowedColumn(String email){
        userRepository.updateIsSendingNotificationAllowedColumn(email);
    }

}

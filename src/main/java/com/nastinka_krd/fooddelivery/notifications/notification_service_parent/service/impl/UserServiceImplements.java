package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.impl;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.User;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.repository.UserRepository;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.UserService;
import com.nastinka_krd.user_management.api.dto.UserDataForNotifications;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplements implements UserService {
    private final UserRepository userRepository;


    @KafkaListener(topics = "user-saving", groupId = "notification-group",
            containerFactory = "userDataKafkaListenerContainerFactory")
    private void listenUserSavingEvent(UserDataForNotifications userDataForNotifications) {
        try {
            if (userRepository.findById(userDataForNotifications.getId()).isEmpty()){
                User user = User.builder()
                        .id(userDataForNotifications.getId())
                        .email(userDataForNotifications.getEmail())
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

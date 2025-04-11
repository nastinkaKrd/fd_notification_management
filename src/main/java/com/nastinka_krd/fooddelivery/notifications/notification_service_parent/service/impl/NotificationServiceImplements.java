package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.impl;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.dto.NotificationDto;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.mapper.NotificationMapper;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.repository.NotificationRepository;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.NotificationService;
import com.nastinka_krd.user_management.api.dto.EmailNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplements implements NotificationService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendingUserEmail;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;



    @KafkaListener(topics = "email-confirmation", groupId = "notification-group",
            containerFactory = "emailNotificationKafkaListenerContainerFactory")
    public void listenRegisterEvent(EmailNotification emailNotification) {
        try {
            System.out.println("hi " + emailNotification);
            String subject = "Email confirmation";
            String text = "Follow this link to confirm your registration: http://localhost:4200/auth/email-confirm?email=" +
                    emailNotification.getEmail() + "&secret-key=" + emailNotification.getSecretKey();
            System.out.println(sendMessageOnEmail(emailNotification.getEmail(), subject, text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "reset-password", groupId = "notification-group",
            containerFactory = "emailNotificationKafkaListenerContainerFactory")
    public void listenResetPasswordEvent(EmailNotification emailNotification) {
        try {
            String subject = "Confirmation of changing password";
            String text = "Follow this link to change password: http://localhost:4200/api/auth/change-password?secret-key=" + emailNotification.getSecretKey();
            System.out.println(sendMessageOnEmail(emailNotification.getEmail(), subject, text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String sendMessageOnEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendingUserEmail);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        return "The letter has been sent to your email. Please check your email...";
    }

    @Override
    public List<NotificationDto> getNotifications() {
        return notificationMapper.toDtos(notificationRepository.findAll());
    }

    @Override
    public void addNotification(NotificationDto notificationDto) {
        notificationRepository.save(notificationMapper.toDomain(notificationDto));
    }

}

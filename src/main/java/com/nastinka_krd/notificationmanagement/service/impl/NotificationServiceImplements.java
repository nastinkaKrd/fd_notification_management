package com.nastinka_krd.notificationmanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nastinka_krd.notificationmanagement.dto.EmailNotification;
import com.nastinka_krd.notificationmanagement.dto.NotificationDto;
import com.nastinka_krd.notificationmanagement.mapper.NotificationMapper;
import com.nastinka_krd.notificationmanagement.repository.NotificationRepository;
import com.nastinka_krd.notificationmanagement.service.NotificationService;
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
    private final ObjectMapper objectMapper;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendingUserEmail;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;



    @KafkaListener(topics = "email-confirmation", groupId = "notification-group")
    public void listenRegisterEvent(String message) {
        try {
            EmailNotification confirmationEmailNotification = objectMapper.readValue(message, EmailNotification.class);
            String subject = "Email confirmation";
            String text = "Follow this link to confirm your registration: http://localhost:8082/auth/email-confirm/" +
                    confirmationEmailNotification.getEmail() + "?secret-key=" + confirmationEmailNotification.getSecretKey();
            System.out.println(sendMessageOnEmail(confirmationEmailNotification.getEmail(), subject, text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "reset-password", groupId = "notification-group")
    public void listenResetPasswordEvent(String message) {
        try {
            EmailNotification confirmationEmailNotification = objectMapper.readValue(message, EmailNotification.class);
            String subject = "Confirmation of changing password";
            String text = "Your verification secret key for changing password: "
                    + confirmationEmailNotification.getSecretKey()
                    + " Follow this link to change password: http://localhost:8082/auth/change-password";
            System.out.println(sendMessageOnEmail(confirmationEmailNotification.getEmail(), subject, text));
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

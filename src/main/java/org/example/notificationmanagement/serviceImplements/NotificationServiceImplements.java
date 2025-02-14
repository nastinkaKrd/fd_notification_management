package org.example.notificationmanagement.serviceImplements;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.notificationmanagement.dto.emailNotification;
import org.example.notificationmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImplements implements NotificationService {
    private final ObjectMapper objectMapper;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendingUserEmail;

    public NotificationServiceImplements(ObjectMapper objectMapper, JavaMailSender javaMailSender) {
        this.objectMapper = objectMapper;
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(topics = "email-confirmation", groupId = "notification-group")
    public void listenRegisterEvent(String message) {
        try {
            emailNotification confirmationEmailNotification = objectMapper.readValue(message, emailNotification.class);
            String subject = "Confirmation of registration";
            String text = "Follow this link to confirm your registration: http://localhost:8082/auth/email-confirm/" + confirmationEmailNotification.getSecretKey();
            System.out.println(sendVerificationMessageOnEmail(confirmationEmailNotification.getEmail(), subject, text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "reset-password", groupId = "notification-group")
    public void listenResetPasswordEvent(String message) {
        try {
            emailNotification confirmationEmailNotification = objectMapper.readValue(message, emailNotification.class);
            String subject = "Confirmation of changing password";
            String text = "Your verification secret key for changing password: "
                    + confirmationEmailNotification.getSecretKey()
                    + " Follow this link to change password: http://localhost:8082/auth/change-password";
            System.out.println(sendVerificationMessageOnEmail(confirmationEmailNotification.getEmail(), subject, text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String sendVerificationMessageOnEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendingUserEmail);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        return "The letter has been sent to your email. Please check your email...";
    }

}

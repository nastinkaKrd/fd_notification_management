package com.nastinka_krd.fooddelivery.notifications.notification_service_parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationManagementApplication.class, args);
    }

}

package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "is_sending_notification_allowed")
    private Boolean isSendingNotificationAllowed;
}

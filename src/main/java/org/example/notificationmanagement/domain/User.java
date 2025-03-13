package org.example.notificationmanagement.domain;

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
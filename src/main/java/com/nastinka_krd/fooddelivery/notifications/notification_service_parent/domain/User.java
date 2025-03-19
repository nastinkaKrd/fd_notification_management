package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fd_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Column(name = "is_sending_notification_allowed")
    private Boolean isSendingNotificationAllowed;
}

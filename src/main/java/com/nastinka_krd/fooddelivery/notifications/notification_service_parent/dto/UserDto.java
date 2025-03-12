package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    private String email;

    private Boolean isSendingNotificationAllowed;
}

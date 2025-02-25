package com.nastinka_krd.fooddelivery.notifications.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    private String email;

    private Boolean isSendingNotificationAllowed;
}

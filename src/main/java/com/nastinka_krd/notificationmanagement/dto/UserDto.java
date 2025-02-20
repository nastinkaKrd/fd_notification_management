package com.nastinka_krd.notificationmanagement.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    private String email;

    private Boolean isSendingNotificationAllowed;
}

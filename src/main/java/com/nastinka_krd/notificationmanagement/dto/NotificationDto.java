package com.nastinka_krd.notificationmanagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationDto {
    private Integer id;

    private String title;

    private String description;

    private Date planningDate;
}

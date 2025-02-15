package org.example.notificationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailNotification {
    private String email;
    private String secretKey;
}
package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.controller;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.service.NotificationService;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<NotificationDto> getNotifications(){
        return notificationService.getNotifications();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addNotification(@RequestBody NotificationDto notificationDto){
        notificationService.addNotification(notificationDto);
    }
}

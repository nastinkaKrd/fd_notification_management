package com.nastinka_krd.fooddelivery.notifications.controller;

import com.nastinka_krd.fooddelivery.notifications.service.NotificationService;
import com.nastinka_krd.fooddelivery.notifications.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> getNotifications(){
        return notificationService.getNotifications();
    }

    @PostMapping
    public void addNotification(@RequestBody NotificationDto notificationDto){
        notificationService.addNotification(notificationDto);
    }
}

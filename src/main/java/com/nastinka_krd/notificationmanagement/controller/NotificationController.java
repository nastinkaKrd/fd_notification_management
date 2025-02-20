package com.nastinka_krd.notificationmanagement.controller;

import com.nastinka_krd.notificationmanagement.dto.NotificationDto;
import com.nastinka_krd.notificationmanagement.service.NotificationService;
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

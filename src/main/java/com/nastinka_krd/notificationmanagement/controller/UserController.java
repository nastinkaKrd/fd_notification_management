package com.nastinka_krd.notificationmanagement.controller;

import com.nastinka_krd.notificationmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PatchMapping("/{email}")
    public void updateIsSendingNotificationAllowedColumn(@PathVariable(name = "email") String email){
        userService.updateIsSendingNotificationAllowedColumn(email);
    }


}

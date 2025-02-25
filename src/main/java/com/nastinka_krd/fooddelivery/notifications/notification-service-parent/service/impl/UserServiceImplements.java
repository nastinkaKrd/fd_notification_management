package com.nastinka_krd.fooddelivery.notifications.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nastinka_krd.fooddelivery.notifications.mapper.UserMapper;
import com.nastinka_krd.fooddelivery.notifications.repository.UserRepository;
import com.nastinka_krd.fooddelivery.notifications.service.UserService;
import com.nastinka_krd.fooddelivery.notifications.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplements implements UserService {
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @KafkaListener(topics = "user-saving", groupId = "notification-group")
    private void listenUserSavingEvent(String message) {
        try {
            UserDto userDto = objectMapper.readValue(message, UserDto.class);
            if (userRepository.findById(userDto.getId()).isEmpty()){
                userRepository.save(userMapper.toDomain(userDto));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateIsSendingNotificationAllowedColumn(String email){
        userRepository.updateIsSendingNotificationAllowedColumn(email);
    }

}

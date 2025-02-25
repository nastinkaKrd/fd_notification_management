package com.nastinka_krd.fooddelivery.notifications.mapper;

import com.nastinka_krd.fooddelivery.notifications.domain.Notification;
import com.nastinka_krd.fooddelivery.notifications.dto.NotificationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto>{
}

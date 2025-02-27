package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.mapper;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.Notification;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.dto.NotificationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto>{
}

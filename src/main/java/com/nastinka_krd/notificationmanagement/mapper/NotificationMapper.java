package com.nastinka_krd.notificationmanagement.mapper;

import com.nastinka_krd.notificationmanagement.domain.Notification;
import com.nastinka_krd.notificationmanagement.dto.NotificationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto>{
}

package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.mapper;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.User;
import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}

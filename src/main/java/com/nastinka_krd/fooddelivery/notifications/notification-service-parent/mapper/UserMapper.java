package com.nastinka_krd.fooddelivery.notifications.mapper;

import com.nastinka_krd.fooddelivery.notifications.domain.User;
import com.nastinka_krd.fooddelivery.notifications.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}

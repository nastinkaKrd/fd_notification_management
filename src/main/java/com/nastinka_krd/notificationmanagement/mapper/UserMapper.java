package com.nastinka_krd.notificationmanagement.mapper;

import com.nastinka_krd.notificationmanagement.domain.User;
import com.nastinka_krd.notificationmanagement.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}

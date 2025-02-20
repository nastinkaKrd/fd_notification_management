package com.nastinka_krd.notificationmanagement.mapper;

import java.util.List;

public interface BaseMapper <Entity, Dto>{
    Entity toDomain(Dto dto);

    Dto toDto(Entity entity);

    List<Entity> toDomains(List<Dto> dtos);

    List<Dto> toDtos(List<Entity> entities);
}

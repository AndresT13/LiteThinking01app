package com.microservices.one.services;

import com.microservices.one.models.dto.RoomDto;
import com.microservices.one.utils.Mappers.MapperObjectsRoom;

import java.util.List;

public interface IRoomService extends MapperObjectsRoom {

    List<RoomDto> findAll();

    RoomDto findById(Long id);

    RoomDto create(RoomDto roomDto);

    void delete(Long id);

    boolean existById(Long id);
}

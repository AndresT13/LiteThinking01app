package com.microservices.one.services;

import com.microservices.one.models.dto.RoomTypeDto;
import com.microservices.one.utils.Mappers.MapperObjectsRoomType;

import java.util.List;

public interface IRoomTypeService extends MapperObjectsRoomType {

    List<RoomTypeDto> findAll();

    RoomTypeDto findById(Long id);

    RoomTypeDto createRoomType(RoomTypeDto roomTypeDto);

    void deleteRoomType(Long id);

    boolean existById(Long id);
}

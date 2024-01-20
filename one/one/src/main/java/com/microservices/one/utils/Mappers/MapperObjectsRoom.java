package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.RoomDto;
import com.microservices.one.models.entities.RoomEntity;

public interface MapperObjectsRoom {

    public static RoomDto RoomEntityToRoomDto (RoomEntity roomsEntity){

        return RoomDto.builder()
                .id(roomsEntity.getId())
                .numberRooms(roomsEntity.getNumberRooms())
                .codeRoom(roomsEntity.getCodeRoom())
                .category(roomsEntity.getCategory())
                .status(roomsEntity.getStatus())
                .build();

    }

    public static RoomEntity roomCtoToRoomEntity(RoomDto roomDto) {
        return RoomEntity.builder()
                .id(roomDto.getId())
                .numberRooms(roomDto.getNumberRooms())
                .codeRoom(roomDto.getNumberRooms())
                .category(roomDto.getCategory())
                .status(roomDto.getStatus())
                .build();
    }
}

package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.RoomPriceDto;
import com.microservices.one.models.dto.RoomTypeDto;
import com.microservices.one.models.entities.RoomPriceEntity;
import com.microservices.one.models.entities.RoomTypeEntity;

public interface MapperObjectsRoomType {

    public static RoomTypeDto roomTypesEntityToRoomTypesDto (RoomTypeEntity roomTypeEntity) {
        return RoomTypeDto.builder()
                .id(roomTypeEntity.getId())
                .codeRoom(roomTypeEntity.getCategory())
                .livingRoom(roomTypeEntity.getLivingRoom())
                .loung(roomTypeEntity.getLoung())
                .beds(roomTypeEntity.getBeds())
                .bathroom(roomTypeEntity.getBathroom())
                .category(roomTypeEntity.getCategory())
                .exteriors(roomTypeEntity.getExteriors())
                .mezzanine(roomTypeEntity.getMezzanine())
                .terrace(roomTypeEntity.getTerrace())
                .status(roomTypeEntity.getStatus())
                .build();
    }


    public static RoomTypeEntity roomTypesToRoDtoomTypesEntity (RoomTypeDto roomTypeDto) {
        return RoomTypeEntity.builder()
                .id(roomTypeDto.getId())
                .codeRoom(roomTypeDto.getCodeRoom())
                .livingRoom(roomTypeDto.getLivingRoom())
                .loung(roomTypeDto.getLoung())
                .beds(roomTypeDto.getBeds())
                .category(roomTypeDto.getCategory())
                .exteriors(roomTypeDto.getExteriors())
                .mezzanine(roomTypeDto.getMezzanine())
                .terrace(roomTypeDto.getTerrace())
                .status(roomTypeDto.getStatus())
                .build();
    }



}

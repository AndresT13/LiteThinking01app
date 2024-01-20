package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.RoomPriceDto;
import com.microservices.one.models.entities.RoomPriceEntity;

public interface MapperObjectsRoomPrice {

    public static RoomPriceDto roomPriceEntityToRoomPriceDto(RoomPriceEntity roomPriceEntity){
        return RoomPriceDto.builder()
                .id(roomPriceEntity.getId())
                .price(roomPriceEntity.getPrice())
                .category(roomPriceEntity.getCategory())
                .seasson(roomPriceEntity.getSeasson())
                .status(roomPriceEntity.getStatus())
                .build();
    }

    public static RoomPriceEntity roomPriceDtoToRoomPriceEntity(RoomPriceDto roomPriceDto){
        return RoomPriceEntity.builder()
                .id(roomPriceDto.getId())
                .price(roomPriceDto.getPrice())
                .category(roomPriceDto.getCategory())
                .seasson(roomPriceDto.getSeasson())
                .status(roomPriceDto.getStatus())
                .build();
    }
}

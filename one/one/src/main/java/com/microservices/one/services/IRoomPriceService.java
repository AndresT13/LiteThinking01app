package com.microservices.one.services;

import com.microservices.one.models.dto.RoomPriceDto;
import com.microservices.one.utils.Mappers.MapperObjectsRoomPrice;

import java.util.List;

public interface IRoomPriceService extends MapperObjectsRoomPrice {

    List<RoomPriceDto> findAll();

    RoomPriceDto findById(Long id);

    RoomPriceDto createRoomPrice(RoomPriceDto roomPriceDto);

    void deleteRoomPrice(Long id);

    boolean existById(Long id);
}

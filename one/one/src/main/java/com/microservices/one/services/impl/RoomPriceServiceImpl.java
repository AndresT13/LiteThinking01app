package com.microservices.one.services.impl;

import com.microservices.one.models.dto.RoomDto;
import com.microservices.one.models.dto.RoomPriceDto;
import com.microservices.one.models.entities.RoomPriceEntity;
import com.microservices.one.repositories.dao.RoomPriceDao;
import com.microservices.one.services.IRoomPriceService;
import com.microservices.one.utils.Mappers.MapperObjectsRoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomPriceServiceImpl implements IRoomPriceService {
    private final RoomPriceDao roomPriceRepository;

    @Autowired
    public RoomPriceServiceImpl(RoomPriceDao roomPriceRepository){
        this.roomPriceRepository = roomPriceRepository;
    }

    @Override
    public List<RoomPriceDto> findAll() {
        List<RoomPriceEntity> entityList = roomPriceRepository.findAll();
        List<RoomPriceDto> roomPriceDtoList = new ArrayList<>();
            for (RoomPriceEntity entity : entityList) {
                roomPriceDtoList.add(MapperObjectsRoomPrice.roomPriceEntityToRoomPriceDto(entity));
            }
        return roomPriceDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public RoomPriceDto findById(Long id) {

        Optional<RoomPriceEntity> entity = roomPriceRepository.findById(id);
        RoomPriceDto roomPriceDto = null;

        if(entity.isPresent()){
            roomPriceDto = MapperObjectsRoomPrice.roomPriceEntityToRoomPriceDto(entity.get());
        }
        return roomPriceDto;
    }

    @Override
    public RoomPriceDto createRoomPrice(RoomPriceDto roomPriceDto) {
        Optional<RoomPriceEntity> entity = roomPriceRepository.findById(roomPriceDto.getId());
        RoomPriceEntity roomPriceEntity = null;
        if(!entity.isPresent()){
            roomPriceEntity = roomPriceRepository.save(MapperObjectsRoomPrice.roomPriceDtoToRoomPriceEntity(roomPriceDto));
        }
        return MapperObjectsRoomPrice.roomPriceEntityToRoomPriceDto(roomPriceEntity);
    }

    @Override
    public void deleteRoomPrice(Long id) {
        Optional<RoomPriceEntity> entity = roomPriceRepository.findById(id);

        if(entity.isPresent()){
            entity.get().setStatus(0);
            roomPriceRepository.save(entity.get());
        }


    }

    @Override
    public boolean existById(Long id) {
        return roomPriceRepository.existsById(id);
    }
}

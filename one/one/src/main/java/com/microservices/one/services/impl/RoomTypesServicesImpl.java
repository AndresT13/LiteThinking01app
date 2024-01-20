package com.microservices.one.services.impl;

import com.microservices.one.models.dto.RoomTypeDto;
import com.microservices.one.models.entities.RoomTypeEntity;
import com.microservices.one.repositories.dao.RoomTypeDao;
import com.microservices.one.services.IRoomTypeService;
import com.microservices.one.utils.Mappers.MapperObjectsRoom;
import com.microservices.one.utils.Mappers.MapperObjectsRoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypesServicesImpl implements IRoomTypeService {

    private static RoomTypeDao roomTypeRepository;

    @Autowired
    public RoomTypesServicesImpl(RoomTypeDao roomTypeRepository){
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public List<RoomTypeDto> findAll() {
        List<RoomTypeEntity> RoomTypeEntityList = roomTypeRepository.findAll();
        List<RoomTypeDto> roomTypeDto = new ArrayList<>();
            for(RoomTypeEntity entity:RoomTypeEntityList ){
                roomTypeDto.add(MapperObjectsRoomType.roomTypesEntityToRoomTypesDto(entity));
            }
        return roomTypeDto;
    }

    @Override
    public RoomTypeDto findById(Long id) {
        Optional<RoomTypeEntity> entity = roomTypeRepository.findById(id);
        RoomTypeDto roomTypeDto = null;
            if(entity.isPresent())
                roomTypeDto = MapperObjectsRoomType.roomTypesEntityToRoomTypesDto(entity.get());
        return roomTypeDto;
    }

    @Override
    public RoomTypeDto createRoomType(RoomTypeDto roomTypeDto) {
        Optional<RoomTypeEntity> entity = roomTypeRepository.findById(roomTypeDto.getId());
        RoomTypeEntity roomTypeEntity = null;
        if(!entity.isPresent()){
            roomTypeEntity = roomTypeRepository.save(MapperObjectsRoomType.roomTypesToRoDtoomTypesEntity(roomTypeDto));
        }
        return MapperObjectsRoomType.roomTypesEntityToRoomTypesDto(roomTypeEntity);
    }

    @Override
    public void deleteRoomType(Long id) {
        Optional<RoomTypeEntity> entity = roomTypeRepository.findById(id);

            if(entity.isPresent()){
                entity.get().setStatus(0);
                roomTypeRepository.save(entity.get());
            }

    }

    @Override
    public boolean existById(Long id) {
        return roomTypeRepository.existsById(id);
    }
}

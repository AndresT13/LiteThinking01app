package com.microservices.one.services.impl;

import com.microservices.one.models.dto.RoomDto;
import com.microservices.one.models.entities.RoomEntity;
import com.microservices.one.repositories.dao.RoomDao;
import com.microservices.one.services.IRoomService;
import com.microservices.one.utils.Mappers.MapperObjectsRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements IRoomService {

    private static RoomDao roomRepository;

    @Autowired
    public RoomServiceImpl(RoomDao roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDto> findAll() {
        List<RoomEntity> roomEntityList = roomRepository.findAll();
        List<RoomDto> roomDtoList = new ArrayList<>();
            for(RoomEntity entity : roomEntityList ){
                          roomDtoList.add(MapperObjectsRoom.RoomEntityToRoomDto(entity));
            }
        return roomDtoList;
    }
    @Transactional(readOnly = true)
    @Override
    public RoomDto findById(Long id) {
        Optional<RoomEntity> entity = roomRepository.findById(id);
        RoomDto roomDto = null;
            if(entity.isPresent())
                roomDto = MapperObjectsRoom.RoomEntityToRoomDto(entity.get());
        return roomDto;
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        Optional<RoomEntity> entity = roomRepository.findById(roomDto.getId());
        RoomEntity roomEntity = null;
            if(!entity.isPresent()){
                roomEntity = roomRepository.save(MapperObjectsRoom.roomCtoToRoomEntity(roomDto));
            }
        return MapperObjectsRoom.RoomEntityToRoomDto(roomEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<RoomEntity> roomEntity = roomRepository.findById(id);
            if(roomEntity.isPresent()){
                roomEntity.get().setStatus(0);
                roomRepository.save(roomEntity.get());
            }

    }

    @Override
    public boolean existById(Long id) {
        return roomRepository.existsById(id);
    }
}

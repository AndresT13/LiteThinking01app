package com.microservices.one.services.impl;

import com.microservices.one.models.dto.SeassonDto;
import com.microservices.one.models.entities.SeassonEntity;
import com.microservices.one.repositories.dao.SeassonDao;
import com.microservices.one.services.ISeassonService;
import com.microservices.one.utils.Mappers.MapperObjectsRoom;
import com.microservices.one.utils.Mappers.MapperObjectsSeasson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeassonServiceImpl implements ISeassonService {

    private final SeassonDao seassonRepository;

    @Autowired
    public SeassonServiceImpl(SeassonDao seassonRepository){
        this.seassonRepository = seassonRepository;
    }

    @Override
    public List<SeassonDto> findAll() {
        List<SeassonEntity> entityList = seassonRepository.findAll();
        List<SeassonDto> seassonDtoList = new ArrayList<>();
            for(SeassonEntity entity : entityList ){
                seassonDtoList.add(MapperObjectsSeasson.seassonEntitySeassonDto(entity));
            }
        return seassonDtoList;
    }

    @Override
    public SeassonDto findById(Long id) {
        Optional<SeassonEntity> entity = seassonRepository.findById(id);
        SeassonDto seassonDto = null;
            if(entity.isPresent()){
                seassonDto = MapperObjectsSeasson.seassonEntitySeassonDto(entity.get());
            }
        return seassonDto;
    }

    @Override
    public SeassonDto createSeasson(SeassonDto seassonDto) {
        Optional<SeassonEntity> entity = seassonRepository.findById(seassonDto.getId());
        SeassonEntity seassonEntity = null;
            if(!entity.isPresent()){
                seassonEntity = seassonRepository.save(MapperObjectsSeasson.seassonDtoToSeassonEntity(seassonDto));
            }
        return MapperObjectsSeasson.seassonEntitySeassonDto(seassonEntity);
    }

    @Override
    public void deleteSeasson(Long id) {
        Optional<SeassonEntity> entity = seassonRepository.findById(id);

            if(entity.isPresent()){
                entity.get().setStatus(0);
                seassonRepository.save(entity.get());
            }


    }

    @Override
    public boolean existById(Long id) {
        return seassonRepository.existsById(id);
    }
}

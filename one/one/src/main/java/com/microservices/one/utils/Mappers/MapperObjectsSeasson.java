package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.SeassonDto;
import com.microservices.one.models.entities.SeassonEntity;

public interface MapperObjectsSeasson {

    public static SeassonDto seassonEntitySeassonDto(SeassonEntity seassonEntity) {
       return SeassonDto.builder()
                .id(seassonEntity.getId())
                .category(seassonEntity.getCategory())
                .price(seassonEntity.getPrice())
                .dateIn(seassonEntity.getDateIn())
                .dateOut(seassonEntity.getDateOut())
               .status(seassonEntity.getStatus())
                .build();
    }

    public static SeassonEntity seassonDtoToSeassonEntity(SeassonDto seassonDto) {
        return SeassonEntity.builder()
                .id(seassonDto.getId())
                .category(seassonDto.getCategory())
                .price(seassonDto.getPrice())
                .dateIn(seassonDto.getDateIn())
                .dateOut(seassonDto.getDateOut())
                .status(seassonDto.getStatus())
                .build();
    }




}

package com.microservices.one.services;

import com.microservices.one.models.dto.SeassonDto;
import com.microservices.one.utils.Mappers.MapperObjectsSeasson;

import java.util.List;

public interface ISeassonService extends MapperObjectsSeasson {

    List<SeassonDto> findAll();

    SeassonDto findById(Long id);

    SeassonDto createSeasson(SeassonDto seassonDto);

    void deleteSeasson(Long id);

    boolean existById(Long id);
}

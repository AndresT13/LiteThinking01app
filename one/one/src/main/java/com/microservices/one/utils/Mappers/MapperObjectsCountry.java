package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.CountryDto;
import com.microservices.one.models.entities.CountryEntity;
import com.microservices.one.repositories.dao.CountryDao;

public interface MapperObjectsCountry {

    public static CountryDto countryEntityToCountryDto (CountryEntity countryEntity){
        return CountryDto.builder()
                .id(countryEntity.getId())
                .codeCountry(countryEntity.getCodeCountry())
                .nameCountry(countryEntity.getNameCountry())
                .status(countryEntity.getStatus())
                .build();
    }

    public static CountryEntity countryDtoToCountryEntity(CountryDto countryDto){
        return CountryEntity.builder()
                .id(countryDto.getId())
                .codeCountry(countryDto.getCodeCountry())
                .nameCountry(countryDto.getNameCountry())
                .status(countryDto.getStatus())
                .build();
    }
}

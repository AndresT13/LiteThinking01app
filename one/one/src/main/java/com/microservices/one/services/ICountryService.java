package com.microservices.one.services;

import com.microservices.one.models.dto.CountryDto;
import com.microservices.one.utils.Mappers.MapperObjectsCountry;

import java.util.List;

public interface ICountryService extends MapperObjectsCountry {

    List<CountryDto> findAll();

    CountryDto findById(Long id);

    CountryDto createCountry(CountryDto countryDto);

    void deleteCountry(Long id);

    boolean existsById(Long id);


}

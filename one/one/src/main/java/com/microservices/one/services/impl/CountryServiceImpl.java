package com.microservices.one.services.impl;

import com.microservices.one.models.dto.CountryDto;
import com.microservices.one.models.entities.CountryEntity;
import com.microservices.one.repositories.dao.CountryDao;
import com.microservices.one.services.ICountryService;
import com.microservices.one.utils.Mappers.MapperObjectsCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {


    private final CountryDao countryRepository;

    @Autowired
    public CountryServiceImpl(CountryDao countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDto> findAll() {
        List<CountryEntity> countryEntityList = countryRepository.findAll();
        List<CountryDto> countryDtoList = new ArrayList<>();
            for(CountryEntity entity : countryEntityList) {
                countryDtoList.add(MapperObjectsCountry.countryEntityToCountryDto(entity));
            }
        return countryDtoList;
    }

    @Override
    public CountryDto findById(Long id) {

        Optional<CountryEntity> countryEntityList = countryRepository.findById(id);
        CountryDto countryDto = null;

        if(countryEntityList.isPresent())
            countryDto = MapperObjectsCountry.countryEntityToCountryDto(countryEntityList.get());
        return countryDto;
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Optional<CountryEntity> entity = countryRepository.findById(countryDto.getId());
        CountryEntity countryEntity = null;
        if(entity.isPresent()){
            countryEntity = countryRepository.save(MapperObjectsCountry.countryDtoToCountryEntity(countryDto));
        }
        return MapperObjectsCountry.countryEntityToCountryDto(countryEntity);
    }

    @Override
    public void deleteCountry(Long id) {
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);

        if(countryEntity.isPresent())
            countryEntity.get().setStatus(0);
            countryRepository.save(countryEntity.get());
    }

    @Override
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }
}

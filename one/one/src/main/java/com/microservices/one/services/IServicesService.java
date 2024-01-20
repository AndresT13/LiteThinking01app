package com.microservices.one.services;

import com.microservices.one.models.dto.ServiceDto;
import com.microservices.one.models.entities.ServicesEntity;
import com.microservices.one.utils.Mappers.MapperObjectsServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServicesService extends MapperObjectsServices {

    List<ServiceDto> findAll();

    ServiceDto findByid(Long id);

    ServiceDto createService(ServiceDto serviceDto);

    void deleteService(Long id);

    boolean existById(Long id);


}

package com.microservices.one.services;

import com.microservices.one.models.dto.ServiceTypeDto;
import com.microservices.one.utils.Mappers.MapperObjectsServiceType;

import java.util.List;

public interface IServicesTypeService extends MapperObjectsServiceType {

    List<ServiceTypeDto> findAll();

    ServiceTypeDto findById(Long id);

    ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto);


    void deleteServiceType(Long id);

    boolean existById(Long id);

}

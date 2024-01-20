package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.ServiceDto;
import com.microservices.one.models.entities.ServicesEntity;

public interface MapperObjectsServices {

    public static ServiceDto serviceEntityToServiceDto(ServicesEntity servicesEntity){
        return ServiceDto.builder()
                .id(servicesEntity.getId())
                .servicesName(servicesEntity.getServicesName())
                .codeServiceName(servicesEntity.getServicesName())
                .description(servicesEntity.getDescription())
                .price(servicesEntity.getPrice())
                .iva(servicesEntity.getIva())
                .status(servicesEntity.getStatus())
                .build();
    }

    public static ServicesEntity serviceDtoToServiceEntity(ServiceDto serviceDto){
        return ServicesEntity.builder()
                .id(serviceDto.getId())
                .servicesName(serviceDto.getServicesName())
                .codeServiceName(serviceDto.getServicesName())
                .description(serviceDto.getDescription())
                .price(serviceDto.getPrice())
                .iva(serviceDto.getIva())
                .status(serviceDto.getStatus())
                .build();
    }
}

package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.ServiceTypeDto;
import com.microservices.one.models.entities.ServiceTypeEntity;

public interface MapperObjectsServiceType {

    public static ServiceTypeDto serviceTypeEntityToServiceTypeDto(ServiceTypeEntity serviceTypeEntity){
        return ServiceTypeDto.builder()
                .id(serviceTypeEntity.getId())
                .nameServices(serviceTypeEntity.getNameServices())
                .codServiceType(serviceTypeEntity.getCodServiceType())
                .status(serviceTypeEntity.getStatus())
                .build();
    }

    public static ServiceTypeEntity serviceTypeDtoToServiceTypeEntity(ServiceTypeDto serviceTypeDto){
        return ServiceTypeEntity.builder()
                .id(serviceTypeDto.getId())
                .nameServices(serviceTypeDto.getNameServices())
                .codServiceType(serviceTypeDto.getCodServiceType())
                .status(serviceTypeDto.getStatus())
                .build();
    }


}

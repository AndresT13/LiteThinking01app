package com.microservices.one.services.impl;

import com.microservices.one.models.dto.ServiceTypeDto;
import com.microservices.one.models.entities.ServiceTypeEntity;
import com.microservices.one.repositories.dao.ServiceTypeDao;
import com.microservices.one.services.IServicesTypeService;
import com.microservices.one.utils.Mappers.MapperObjectsServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesTypeServiceImpl implements IServicesTypeService {

    private final ServiceTypeDao serviceTypeRepository;

    @Autowired
    public ServicesTypeServiceImpl(ServiceTypeDao serviceTypeRepository){
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public List<ServiceTypeDto> findAll() {
        List<ServiceTypeEntity> serviceTypeEntityList = serviceTypeRepository.findAll();
        List<ServiceTypeDto> serviceTypeDtoList = new ArrayList<>();
            for(ServiceTypeEntity entity: serviceTypeEntityList){
                serviceTypeDtoList.add(MapperObjectsServiceType.serviceTypeEntityToServiceTypeDto(entity));
            }
        return serviceTypeDtoList;
    }

    @Override
    public ServiceTypeDto findById(Long id) {
        Optional<ServiceTypeEntity> entity = serviceTypeRepository.findById(id);
        ServiceTypeDto serviceTypeDto = null;
            if(entity.isPresent()){
                serviceTypeDto = MapperObjectsServiceType.serviceTypeEntityToServiceTypeDto(entity.get());
            }
        return serviceTypeDto;
    }

    @Override
    public ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto) {
        Optional<ServiceTypeEntity> entity = serviceTypeRepository.findById(serviceTypeDto.getId());
        ServiceTypeEntity serviceTypeEntity = null;
                if(!entity.isPresent()){
                    serviceTypeEntity = serviceTypeRepository.save(MapperObjectsServiceType.serviceTypeDtoToServiceTypeEntity(serviceTypeDto));
                }
        return MapperObjectsServiceType.serviceTypeEntityToServiceTypeDto(serviceTypeEntity);
    }

    @Override
    public void deleteServiceType(Long id) {
        Optional<ServiceTypeEntity> entity = serviceTypeRepository.findById(id);
            if(entity.isPresent()){
                entity.get().setStatus(0);
                serviceTypeRepository.save(entity.get());
            }
    }

    @Override
    public boolean existById(Long id) {
        return serviceTypeRepository.existsById(id);
    }
}

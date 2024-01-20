package com.microservices.one.services.impl;

import com.microservices.one.models.dto.ServiceDto;
import com.microservices.one.models.entities.ServicesEntity;
import com.microservices.one.repositories.dao.ServiceDao;
import com.microservices.one.services.IServicesService;
import com.microservices.one.utils.Mappers.MapperObjectsBooking;
import com.microservices.one.utils.Mappers.MapperObjectsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesServiceImp implements IServicesService {


    public static ServiceDao servicesRepository;

    @Autowired
    public ServicesServiceImp(ServiceDao servicesRepository){
        this.servicesRepository = servicesRepository;
    }

    @Override
    public List<ServiceDto> findAll() {
        List<ServicesEntity> servicesEntityList = servicesRepository.findAll();
        List<ServiceDto> serviceDtoList = new ArrayList<>();
            for(ServicesEntity entity : servicesEntityList){
                serviceDtoList.add(MapperObjectsServices.serviceEntityToServiceDto(entity));
            }
        return serviceDtoList;
    }

    @Override
    public ServiceDto findByid(Long id) {
        Optional<ServicesEntity> entity = servicesRepository.findById(id);
        ServiceDto serviceDto = null;
            if(entity.isPresent())
                serviceDto= MapperObjectsServices.serviceEntityToServiceDto(entity.get());

        return serviceDto;
    }

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {
        Optional<ServicesEntity> entity = servicesRepository.findById(serviceDto.getId());
        ServicesEntity servicesEntity = null;
            if(entity.isEmpty()){
                servicesEntity = servicesRepository.save(MapperObjectsServices.serviceDtoToServiceEntity(serviceDto));
            }
        return MapperObjectsServices.serviceEntityToServiceDto(servicesEntity);
    }

    @Override
    public void deleteService(Long id) {
        Optional<ServicesEntity> servicesEntity = servicesRepository.findById(id);
        if(servicesEntity.isPresent()){
            servicesEntity.get().setStatus(0);
            servicesRepository.save(servicesEntity.get());
        }
    }

    @Override
    public boolean existById(Long id) {
        return servicesRepository.existsById(id);
    }
}

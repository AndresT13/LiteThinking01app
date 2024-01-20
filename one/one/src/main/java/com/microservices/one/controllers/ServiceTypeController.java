package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.ServiceTypeDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IClientService;
import com.microservices.one.services.IServicesTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/serviceType")
public class ServiceTypeController {

    private final IServicesTypeService servicesTypeService;

    @Autowired
    public ServiceTypeController(IServicesTypeService servicesTypeService) {
        this.servicesTypeService =  servicesTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getServicesType() {
        List<ServiceTypeDto> getList = servicesTypeService.findAll();
        if (getList == null || getList.isEmpty()) {
            throw new ResourceNotFoundException("clientes");
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);

    }


    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServiceType(@PathVariable Long id) {

        ServiceTypeDto serviceType = servicesTypeService.findById(id);

        if (serviceType == null) {
            throw new ResourceNotFoundException("cliente", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ServiceTypeDto.builder()
                                .id(serviceType.getId())
                                .codServiceType(serviceType.getCodServiceType())
                                .nameServices(serviceType.getNameServices())
                                .status(serviceType.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createServiceType(@RequestBody @Valid ServiceTypeDto serviceType) {

        ServiceTypeDto ServiceTypeSave = null;
        try {
            ServiceTypeSave = servicesTypeService.createServiceType(serviceType);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(ServiceTypeDto.builder()
                            .id(ServiceTypeSave.getId())
                            .codServiceType(ServiceTypeSave.getCodServiceType())
                            .nameServices(ServiceTypeSave.getNameServices())
                            .status(ServiceTypeSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @PutMapping(path = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateServiceType(@RequestBody ServiceTypeDto serviceTypeDto, @PathVariable("id") Long id) {
        ServiceTypeDto serviceTypeUpdate = null;

        try {
            serviceTypeDto.setId(id);
            serviceTypeUpdate = servicesTypeService.createServiceType(serviceTypeDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(serviceTypeDto.builder()
                            .id(serviceTypeUpdate.getId())
                            .codServiceType(serviceTypeUpdate.getCodServiceType())
                            .nameServices(serviceTypeUpdate.getNameServices())
                            .status(serviceTypeUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<?> removeServiceType(@PathVariable Long id) throws Exception {

        try {
            ServiceTypeDto serviceType = servicesTypeService.findById(id);
            servicesTypeService.deleteServiceType(serviceType.getId());
            return new ResponseEntity<>(serviceType, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }







}

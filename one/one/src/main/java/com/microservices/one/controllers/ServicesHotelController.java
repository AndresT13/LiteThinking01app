package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.ServiceDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicesHotel")
public class ServicesHotelController {
    private final IServicesService serviceHotelService;

    @Autowired
    public ServicesHotelController(IServicesService serviceHotelService)
 {
        this.serviceHotelService = serviceHotelService;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients() {
        List<ServiceDto> getList = serviceHotelService.findAll();
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


    @GetMapping(path="/servicesHotel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServiceHotel(@PathVariable Long id) {

        ServiceDto serviceHotel = serviceHotelService.findByid(id);

        if (serviceHotel == null) {
            throw new ResourceNotFoundException("Servicios del Hotel", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ServiceDto.builder()
                                .id(serviceHotel.getId())
                                .servicesName(serviceHotel.getServicesName())
                                .codeServiceName(serviceHotel.getCodeServiceName())
                                .description(serviceHotel.getDescription())
                                .price(serviceHotel.getPrice())
                                .iva(serviceHotel.getIva())
                                .status(serviceHotel.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createServiceHotel(@RequestBody @Valid ServiceDto ServiceHotel) {

        ServiceDto serviceHotelSave = null;
        try {
            serviceHotelSave = serviceHotelService.createService(ServiceHotel);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(ServiceDto.builder()
                            .id(serviceHotelSave.getId())
                            .servicesName(serviceHotelSave.getServicesName())
                            .codeServiceName(serviceHotelSave.getCodeServiceName())
                            .description(serviceHotelSave.getDescription())
                            .price(serviceHotelSave.getPrice())
                            .iva(serviceHotelSave.getIva())
                            .status(serviceHotelSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }



    @PutMapping(path = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateServiceHotel(@RequestBody ServiceDto serviceDto, @PathVariable("id") Long id) {
        ServiceDto serviceHotelUpdate = null;

        try {

            serviceDto.setId(id);
            serviceHotelUpdate = serviceHotelService.createService(serviceDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(ServiceDto.builder()
                            .id(serviceHotelUpdate.getId())
                            .servicesName(serviceHotelUpdate.getServicesName())
                            .codeServiceName(serviceHotelUpdate.getCodeServiceName())
                            .description(serviceHotelUpdate.getDescription())
                            .price(serviceHotelUpdate.getPrice())
                            .iva(serviceHotelUpdate.getIva())
                            .status(serviceHotelUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }



    @DeleteMapping("delete/{id}")
    public  ResponseEntity<?> removeServiceHotel(@PathVariable Long id) throws Exception {

        try {
            ServiceDto serviceHotel = serviceHotelService.findByid(id);
            serviceHotelService.deleteService(serviceHotel.getId());
            return new ResponseEntity<>(serviceHotel, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }






}

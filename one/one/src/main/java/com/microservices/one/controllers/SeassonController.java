package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.SeassonDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IRoomTypeService;
import com.microservices.one.services.ISeassonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seasson")
public class SeassonController {

    private final ISeassonService seassonService;

    @Autowired
    public SeassonController(ISeassonService seassonService) {
        this.seassonService = seassonService;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getSeassons() {
        List<SeassonDto> getList = seassonService.findAll();
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

    @GetMapping(path="/seasson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClient(@PathVariable Long id) {

        SeassonDto seasson = seassonService.findById(id);

        if (seasson == null) {
            throw new ResourceNotFoundException("cliente", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(SeassonDto.builder()
                                .id(seasson.getId())
                                .category(seasson.getCategory())
                                .price(seasson.getPrice())
                                .dateIn(LocalDateTime.now())
                                .dateOut(seasson.getDateOut())
                                .status(seasson.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
    @PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSeasson(@RequestBody @Valid SeassonDto seasson) {

        SeassonDto seassonSave = null;
        try {
            seassonSave = seassonService.createSeasson(seasson);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(SeassonDto.builder()
                            .id(seassonSave.getId())
                            .category(seassonSave.getCategory())
                            .price(seassonSave.getPrice())
                            .dateIn(LocalDateTime.now())
                            .dateOut(seassonSave.getDateOut())
                            .status(seassonSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }


    @PutMapping(path = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSeasson(@RequestBody SeassonDto seassonDto, @PathVariable("id") Long id) {
        SeassonDto seassonUpdate = null;

        try {
            seassonDto.setId(id);
            seassonUpdate = seassonService.createSeasson(seassonDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(SeassonDto.builder()
                            .id(seassonUpdate.getId())
                            .category(seassonUpdate.getCategory())
                            .price(seassonUpdate.getPrice())
                            .dateIn(LocalDateTime.now())
                            .dateOut(seassonUpdate.getDateOut())
                            .status(seassonUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<?> removeSeasson(@PathVariable Long id) throws Exception {

        try {
            SeassonDto seasson = seassonService.findById(id);
            seassonService.deleteSeasson(seasson.getId());
            return new ResponseEntity<>(seasson, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }





}

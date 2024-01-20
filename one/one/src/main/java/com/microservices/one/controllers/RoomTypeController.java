package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.RoomTypeDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IRoomTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoomTypeController {

    private final IRoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(IRoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;

    }


    @GetMapping("/RoomsType")
    public ResponseEntity<?> getRoomsType() {
        List<RoomTypeDto> getList = roomTypeService.findAll();
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

    @GetMapping(path="/roomType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomType(@PathVariable Long id) {

        RoomTypeDto roomType = roomTypeService.findById(id);

        if (roomType == null) {
            throw new ResourceNotFoundException("tipo de habitación", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(RoomTypeDto.builder()
                                .id(roomType.getId())
                                .category(roomType.getCategory())
                                .codeRoom(roomType.getCodeRoom())
                                .beds(roomType.getBeds())
                                .bathroom(roomType.getBathroom())
                                .livingRoom(roomType.getLivingRoom())
                                .loung(roomType.getLoung())
                                .mezzanine(roomType.getMezzanine())
                                .terrace(roomType.getTerrace())
                                .exteriors(roomType.getExteriors())
                                .status(roomType.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping(path = "/roomType/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRoomType(@RequestBody @Valid RoomTypeDto room) {

        RoomTypeDto roomSave = null;
        try {
            roomSave = roomTypeService.createRoomType(room);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(RoomTypeDto.builder()
                            .id(roomSave.getId())
                            .category(roomSave.getCategory())
                            .codeRoom(roomSave.getCodeRoom())
                            .beds(roomSave.getBeds())
                            .bathroom(roomSave.getBathroom())
                            .livingRoom(roomSave.getLivingRoom())
                            .loung(roomSave.getLoung())
                            .mezzanine(roomSave.getMezzanine())
                            .terrace(roomSave.getTerrace())
                            .exteriors(roomSave.getExteriors())
                            .status(roomSave.getStatus())
                            .status(roomSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @PutMapping(path = "/roomType/update/{numberDocument}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRoomType(@RequestBody RoomTypeDto roomTypeDto, @PathVariable("id") Long id) {
        RoomTypeDto roomTypeUpdate = null;

        try {

            roomTypeDto.setId(id);
            roomTypeUpdate = roomTypeService.createRoomType(roomTypeDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(RoomTypeDto.builder()
                            .id(roomTypeUpdate.getId())
                            .category(roomTypeUpdate.getCategory())
                            .codeRoom(roomTypeUpdate.getCodeRoom())
                            .beds(roomTypeUpdate.getBeds())
                            .bathroom(roomTypeUpdate.getBathroom())
                            .livingRoom(roomTypeUpdate.getLivingRoom())
                            .loung(roomTypeUpdate.getLoung())
                            .mezzanine(roomTypeUpdate.getMezzanine())
                            .terrace(roomTypeUpdate.getTerrace())
                            .exteriors(roomTypeUpdate.getExteriors())
                            .status(roomTypeUpdate.getStatus())
                            .status(roomTypeUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }


    @DeleteMapping("roomType/delete/{id}")
    public  ResponseEntity<?> removeRoomType(@PathVariable Long id) throws Exception {

        try {
            RoomTypeDto roomType = roomTypeService.findById(id);
            roomTypeService.deleteRoomType(roomType.getId());
            return new ResponseEntity<>(roomType, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }








}

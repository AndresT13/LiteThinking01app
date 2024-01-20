package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.RoomDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IClientService;
import com.microservices.one.services.IRoomService;
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
public class RoomController {

    private final IRoomService roomService;

    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms() {
        List<RoomDto> getList = roomService.findAll();
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

    @GetMapping(path="/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoom(@PathVariable Long id) {

        RoomDto habitacion = roomService.findById(id);

        if (habitacion == null) {
            throw new ResourceNotFoundException("Habitación", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(RoomDto.builder()
                                .id(habitacion.getId())
                                .numberRooms(habitacion.getNumberRooms())
                                .codeRoom(habitacion.getCodeRoom())
                                .category(habitacion.getCategory())
                                .status(habitacion.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping(path = "/room/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRoom(@RequestBody @Valid RoomDto room) {

        RoomDto roomSave = null;
        try {
            roomSave = roomService.create(room);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(RoomDto.builder()
                            .id(roomSave.getId())
                            .numberRooms(roomSave.getNumberRooms())
                            .codeRoom(roomSave.getCodeRoom())
                            .category(roomSave.getCategory())
                            .status(roomSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }


    @PutMapping(path = "/room/update/{numberDocument}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto, @PathVariable("id") Long id) {
        RoomDto roomUpdate = null;

        try {

            roomDto.setId(id);
            roomUpdate = roomService.create(roomDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(RoomDto.builder()
                            .id(roomUpdate.getId())
                            .numberRooms(roomUpdate.getNumberRooms())
                            .codeRoom(roomUpdate.getCodeRoom())
                            .category(roomUpdate.getCategory())
                            .status(roomUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }

    @DeleteMapping("room/delete/{id}")
    public  ResponseEntity<?> removeRoom(@PathVariable Long id) throws Exception {

        try {
            RoomDto room = roomService.findById(id);
            roomService.delete(room.getId());
            return new ResponseEntity<>(room, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }
}

package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.RoomPriceDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IClientService;
import com.microservices.one.services.IRoomPriceService;
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
public class RoomPriceController {

    private final IRoomPriceService roomPriceService;

    @Autowired
    public RoomPriceController(IRoomPriceService roomPriceService) {
        this.roomPriceService = roomPriceService;
    }

    @GetMapping("/roomsPrices")
    public ResponseEntity<?> getRoomPrices() {
        List<RoomPriceDto> getList = roomPriceService.findAll();
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

    @GetMapping(path="/roomPrice/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomPrice(@PathVariable Long id) {

        RoomPriceDto roomPrice = roomPriceService.findById(id);

        if (roomPrice == null) {
            throw new ResourceNotFoundException("cliente", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(RoomPriceDto.builder()
                                .id(roomPrice.getId())
                                .category(roomPrice.getCategory())
                                .seasson(roomPrice.getSeasson())
                                .price(roomPrice.getPrice())
                                .status(roomPrice.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping(path = "/roomPrice/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@RequestBody @Valid RoomPriceDto roomPrice) {

        RoomPriceDto RoomPriceSave = null;
        try {
            RoomPriceSave = roomPriceService.createRoomPrice(roomPrice);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(RoomPriceDto.builder()
                            .id(RoomPriceSave.getId())
                            .category(RoomPriceSave.getCategory())
                            .seasson(RoomPriceSave.getSeasson())
                            .price(RoomPriceSave.getPrice())
                            .status(RoomPriceSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }


    @PutMapping(path = "/roomPrice/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRoomPrice(@RequestBody RoomPriceDto roomPriceDto, @PathVariable("numberDocument") Long id) {
        RoomPriceDto roomPriceUpdate = null;

        try {

            roomPriceDto.setId(id);
            roomPriceUpdate = roomPriceService.createRoomPrice(roomPriceDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(RoomPriceDto.builder()
                            .id(roomPriceUpdate.getId())
                            .category(roomPriceUpdate.getCategory())
                            .seasson(roomPriceUpdate.getSeasson())
                            .price(roomPriceUpdate.getPrice())
                            .status(roomPriceUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }

    @DeleteMapping("/roomPrice/delete/{id}")
    public  ResponseEntity<?> removeRoomPrice(@PathVariable Long id) throws Exception {

        try {
            RoomPriceDto roomPrice = roomPriceService.findById(id);
            roomPriceService.deleteRoomPrice(roomPrice.getId());
            return new ResponseEntity<>(roomPrice, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }




}

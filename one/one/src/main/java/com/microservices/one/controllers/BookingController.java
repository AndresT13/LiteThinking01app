package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.BookingDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IBookingService;
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
public class BookingController {

    public static IBookingService bookingService;

    @Autowired
    public BookingController( IBookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getBooking(){
        List<BookingDto> getList = bookingService.findAll();
        if(getList == null || getList.isEmpty()){
            throw new ResourceNotFoundException("Reservas");
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(getList)
                        .build(),
                HttpStatus.OK );
    }


    @GetMapping(path = "/booking/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Long id){
        BookingDto reserva = bookingService.findById(id);

        if(reserva == null){
            throw  new ResourceNotFoundException("reserva", "id", id);
        }

        return new ResponseEntity<>(
                    MessageResponse.builder()
                            .object(BookingDto.builder()
                                    .id(reserva.getId())
                                    .codCLient(reserva.getCodCLient())
                                    .identificationNumber(reserva.getIdentificationNumber())
                                    .roomNumber(reserva.getRoomNumber())
                                    .iva(reserva.getIva())
                                    .status(reserva.getStatus())
                                    .dateIn(reserva.getDateIn())
                                    .dateOut(reserva.getDateOut())
                                    .build())
                            .build(),
                HttpStatus.OK);
    }

    @PostMapping(path ="booking/create", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> createBooking(@RequestBody @Valid BookingDto booking){

       BookingDto  bookingSave = null;
       try{
           bookingSave = bookingService.createBooking(booking);
           return new ResponseEntity<>(MessageResponse.builder()
                   .message("Guardado Correctament")
                   .object(BookingDto.builder()
                           .id(bookingSave.getId())
                           .codCLient(bookingSave.getCodCLient())
                           .identificationNumber(bookingSave.getIdentificationNumber())
                           .roomNumber(bookingSave.getRoomNumber())
                           .iva(bookingSave.getIva())
                           .status(bookingSave.getStatus())
                           .dateIn(bookingSave.getDateIn())
                           .dateOut(bookingSave.getDateOut())
                           .build())
                   .build(),
                   HttpStatus.CREATED);
       } catch(DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());
       }
   }

   @PutMapping(path="/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> updateBooking(@RequestBody BookingDto bookingDto, @PathVariable("id") Long id){
        BookingDto bookingUpdate = null;

        try{
            bookingDto.setId(id);
            bookingUpdate = bookingService.createBooking(bookingDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Reserva actualizada correctamente")
                    .object(BookingDto.builder()
                            .id(bookingUpdate.getId())
                            .codCLient(bookingUpdate.getCodCLient())
                            .identificationNumber(bookingUpdate.getIdentificationNumber())
                            .roomNumber(bookingUpdate.getRoomNumber())
                            .iva(bookingDto.getIva())
                            .status(bookingUpdate.getStatus())
                            .dateIn(bookingUpdate.getDateIn())
                            .dateOut(bookingUpdate.getDateOut())
                            .build())
                    .build(),HttpStatus.CREATED);
        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @DeleteMapping(path = "booking/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) throws Exception{

        try {
            BookingDto booking = bookingService.findById(id);
            bookingService.deleteBooking(booking.getId());
            return new ResponseEntity<>(booking, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }

    }



}

package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.BookingDto;
import com.microservices.one.models.entities.BookingEntity;

public interface MapperObjectsBooking {

    public static BookingDto bookingEntityToBookingDto(BookingEntity bookingEntity){
        return BookingDto.builder()
                .id(bookingEntity.getId())
                .codCLient(bookingEntity.getCodCLient())
                .iva(bookingEntity.getIva())
                .identificationNumber(bookingEntity.getIdentificationNumber())
                .roomNumber(bookingEntity.getRoomNumber())
                .dateIn(bookingEntity.getDateIn())
                .dateOut(bookingEntity.getDateOut())
                .status(bookingEntity.getStatus())
                .build();
    }


    public static BookingEntity bookingDtoToBookingEntity( BookingDto bookingDto) {
        return BookingEntity.builder()
                .id(bookingDto.getId())
                .codCLient(bookingDto.getCodCLient())
                .iva(bookingDto.getIva())
                .identificationNumber(bookingDto.getIdentificationNumber())
                .roomNumber(bookingDto.getRoomNumber())
                .dateIn(bookingDto.getDateIn())
                .dateOut(bookingDto.getDateOut())
                .status(bookingDto.getStatus())
                .build();
    }



}

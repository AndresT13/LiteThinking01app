package com.microservices.one.services;

import com.microservices.one.models.dto.BookingDto;
import com.microservices.one.utils.Mappers.MapperObjectsBooking;

import java.util.List;

public interface IBookingService extends MapperObjectsBooking {


    List<BookingDto> findAll();

    BookingDto findById(Long id);

    BookingDto createBooking(BookingDto bookingDto);

    void deleteBooking(Long id);

    boolean existsById(Long id);
}

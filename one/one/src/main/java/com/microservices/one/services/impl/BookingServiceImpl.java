package com.microservices.one.services.impl;

import com.microservices.one.models.dto.BookingDto;
import com.microservices.one.models.entities.BookingEntity;
import com.microservices.one.repositories.dao.BookingDao;
import com.microservices.one.services.IBookingService;
import com.microservices.one.services.IClientService;
import com.microservices.one.utils.Mappers.MapperObjectsBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService {

    private final BookingDao bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingDao bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public List<BookingDto> findAll() {
        List<BookingEntity> bookingEntitiesList = bookingRepository.findAll();
        List<BookingDto> bookingDtoList = new ArrayList<>();
            for(BookingEntity entity: bookingEntitiesList) {
                bookingDtoList.add(MapperObjectsBooking.bookingEntityToBookingDto(entity));
            }
        return bookingDtoList;
    }


    @Transactional(readOnly = true)
    @Override
    public BookingDto findById(Long id) {

        Optional<BookingEntity> entity = bookingRepository.findById(id);
        BookingDto bookingDto = null;

        if(entity.isPresent())
            bookingDto = MapperObjectsBooking.bookingEntityToBookingDto(entity.get());
        return bookingDto;
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Optional<BookingEntity> entity = bookingRepository.findById(bookingDto.getId());
        BookingEntity bookingEntity = null;
        if (!entity.isPresent()) {
            bookingEntity = bookingRepository.save(MapperObjectsBooking.bookingDtoToBookingEntity(bookingDto));
        }
            return MapperObjectsBooking.bookingEntityToBookingDto(bookingEntity);
    }


    @Override
    public void deleteBooking(Long id) {
        Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);

        if(bookingEntity.isPresent()){
            bookingEntity.get().setStatus(0);
            bookingRepository.save(bookingEntity.get());

        }
    }

    @Override
    public boolean existsById(Long id) {
        return bookingRepository.existsById(id);
    }
}

package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<BookingEntity, Long> {

}

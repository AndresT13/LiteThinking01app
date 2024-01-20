package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.RoomPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPriceDao extends JpaRepository<RoomPriceEntity, Long> {
}

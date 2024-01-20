package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeDao extends JpaRepository<RoomTypeEntity, Long> {
}

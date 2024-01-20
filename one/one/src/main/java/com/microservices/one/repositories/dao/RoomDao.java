package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<RoomEntity, Long> {
}

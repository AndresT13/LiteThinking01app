package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDao extends JpaRepository<ServicesEntity, Long> {
}

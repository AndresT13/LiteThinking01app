package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.SeassonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeassonDao extends JpaRepository<SeassonEntity, Long> {
}

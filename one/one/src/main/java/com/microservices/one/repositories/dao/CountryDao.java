package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<CountryEntity, Long> {
}

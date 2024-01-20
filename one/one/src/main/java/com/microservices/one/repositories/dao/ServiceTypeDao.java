package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.ServiceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeDao extends JpaRepository<ServiceTypeEntity, Long> {

}

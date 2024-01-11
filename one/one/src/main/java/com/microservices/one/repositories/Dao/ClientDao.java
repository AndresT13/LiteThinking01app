package com.microservices.one.repositories.Dao;

import ch.qos.logback.core.net.server.Client;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity, Long> {
    @Query("SELECT clients FROM ClientEntity clients WHERE clients.numberDocument= :numberDocument AND clients.status <> 0")
    Optional<ClientEntity> findByNumberDocument(String numberDocument);
}

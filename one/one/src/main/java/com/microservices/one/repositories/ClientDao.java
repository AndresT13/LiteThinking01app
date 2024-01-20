package com.microservices.one.repositories;

import ch.qos.logback.core.net.server.Client;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ClientDao extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByNumberDocument(String numberDocument);


}

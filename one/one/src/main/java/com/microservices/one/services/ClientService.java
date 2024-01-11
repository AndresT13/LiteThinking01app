package com.microservices.one.services;

import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.utils.MapperObjects;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ClientService extends MapperObjects {

    List<ClientDto> listClient();
    List<ClientDto> findById(Long id);
    ResponseEntity<Object> getNumberDocument(String numberDocument);
    ResponseEntity<Object> addClient(ClientDto client);
    ResponseEntity<Object> updateProduct(Long id, ClientDto clientDto);
    void removeClient(Long id);
}
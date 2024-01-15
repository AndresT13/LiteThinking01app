package com.microservices.one.services;

import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.entities.ClientEntity;
import com.microservices.one.utils.MapperObjects;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ClientService extends MapperObjects {

    List<ClientDto> listClient();
    ClientDto findById(Long id);
    ClientDto getNumberDocument(String numberDocument);
    ClientDto addClient(ClientDto clientDto);
    //ClientDto updateProduct(Long id, ClientDto clientDto)  throws Exception;
    void removeClient(Long id) throws Exception;

    boolean existsById(Long id);
}
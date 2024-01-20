package com.microservices.one.services;

import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.utils.Mappers.MapperObjectsClients;

import java.util.List;


public interface IClientService extends MapperObjectsClients {

    List<ClientDto> listClient();
    ClientDto findById(Long id);
    ClientDto getNumberDocument(String numberDocument);
    ClientDto addClient(ClientDto clientDto);
    //ClientDto updateProduct(Long id, ClientDto clientDto)  throws Exception;
    void removeClient(Long id) throws Exception;

    boolean existsById(Long id);
}
package com.microservices.one.services.impl;

import com.microservices.one.exception.ClientNotFoundException;
import com.microservices.one.exception.ClientResponsehandler;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.entities.ClientEntity;
import com.microservices.one.repositories.dao.ClientDao;
import com.microservices.one.services.ClientService;
import com.microservices.one.utils.Constants;
import com.microservices.one.utils.MapperObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao clientRepository;


    @Override
    public List<ClientDto> listClient() {
        List<ClientEntity> productEntityList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();

        for (ClientEntity entity : productEntityList) {
            clientDtoList.add(MapperObjects.clientEntityToClientDto(entity));
        }
        return clientDtoList;
    }

    @Override
    public List<ClientDto> findById(Long id) {

        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        if(clientEntity.isPresent()) {
            return (List<ClientDto>) ClientResponsehandler.generateResponse("Cliente Encontrado", HttpStatus.OK, Optional.ofNullable(clientEntity));
        }else {
            throw new ClientNotFoundException("Cliente no existente en base de datos !");
        }
    }

    @Override
    public ResponseEntity<Object> getNumberDocument(String numberDocument) {
        Optional<ClientEntity> clientEntity = clientRepository.findByNumberDocument(numberDocument);
        ClientDto clientDto = null;
        if(clientEntity.isPresent() && clientEntity.equals(Constants.DOCUMENT)){
            return ClientResponsehandler.generateResponse("Número de documento del cliente encontrado en base ", HttpStatus.OK, clientEntity);
           // return null;
        } else {
            throw new ClientNotFoundException("Número no encontrado en base de datos! ");
        }

    }

    @Override
    public ResponseEntity<Object> addClient(ClientDto client) {
    Optional<ClientEntity> clientEntity = clientRepository.findByNumberDocument(client.getNumberDocument());

        if(!clientEntity.isPresent()) {
            clientRepository.save(MapperObjects.clientDtoToproductEntity(client));
            return ClientResponsehandler.generateResponse("Número de documento del cliente creado con éxito ", HttpStatus.OK, clientEntity);
        } else {
            throw  new ClientNotFoundException("Número no se creó en base de datos");
        }


    }

    @Override
    public ResponseEntity<Object> updateProduct(Long id, ClientDto clientDto) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        if(clientEntity.isPresent()){
            clientEntity.get().setFirstName(clientDto.getFirstName());
            clientEntity.get().setSecondName(clientDto.getSecondName());
            clientEntity.get().setSecondFirstName(clientDto.getSecondFirstName());
            clientEntity.get().setSecondLastName(clientDto.getSecondLastName());
            clientEntity.get().setDocumentType(clientDto.getDocumentType());
            clientEntity.get().setNumberDocument(Constants.DOCUMENT.toString());
            clientEntity.get().setNumberPhone(clientDto.getNumberPhone());
            clientEntity.get().setMovil(clientDto.getMovil());
            clientEntity.get().setEmail(clientDto.getEmail());
            clientEntity.get().setAddress(clientDto.getAddress());
            clientEntity.get().setCity(clientDto.getCity());
            clientEntity.get().setStatus(clientDto.getStatus());
            clientRepository.save(clientEntity.get());
            return ClientResponsehandler.generateResponse("cliente actualizado con éxito en base", HttpStatus.OK, clientEntity);

        } else {
            throw  new ClientNotFoundException("Número no se creó en base de datos");
        }
    }

    @Override
    public void  removeClient(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        if (clientEntity.isPresent()) {
            clientEntity.get().setStatus(0);
            clientRepository.save(clientEntity.get());
            ClientResponsehandler.generateResponse("cliente eliminado con éxito", HttpStatus.OK, clientEntity);
        } else {
            throw new ClientNotFoundException("él cliente no existe en base de datos");
        }
    }

}

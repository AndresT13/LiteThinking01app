package com.microservices.one.services.impl;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.entities.ClientEntity;
import com.microservices.one.repositories.Dao.ClientDao;
import com.microservices.one.services.ClientService;
import com.microservices.one.utils.Constants;
import com.microservices.one.utils.MapperObjects;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    @Transactional(readOnly = true)
    @Override
    public ClientDto findById(Long id) {

        Optional<ClientEntity> entity = clientRepository.findById(id);
        ClientDto clientDto = null;

        if (entity.isPresent())
           clientDto = MapperObjects.clientEntityToClientDto(entity.get());

        return clientDto;
    }


    @Transactional(readOnly = true)
    @Override
    public ClientDto getNumberDocument(String numberDocument) {


        Optional<ClientEntity> clientEntity = clientRepository.findByNumberDocument(numberDocument);
        ClientDto clientDto = null;

        if (clientEntity.isPresent())
            clientDto = MapperObjects.clientEntityToClientDto(clientEntity.get());
        return clientDto;

    }


    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Optional<ClientEntity> entity = clientRepository.findById(clientDto.getId());
        ClientEntity client = null;
        if (!entity.isPresent()) {
            client = clientRepository.save(MapperObjects.clientDtoToClientEntity(clientDto));
        }

        return MapperObjects.clientEntityToClientDto(client);
    }

   /* @Override
    public ClientDto updateProduct(Long id, ClientDto clientDto)  throws Exception {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);



        if (clientEntity.isPresent()) {
            clientEntity.get().setFirstName(clientDto.getFirstName());
            clientEntity.get().setSecondName(clientDto.getSecondName());
            clientEntity.get().setSecondFirstName(clientDto.getSecondFirstName());
            clientEntity.get().setSecondLastName(clientDto.getSecondLastName());
            clientEntity.get().setDocumentType(clientDto.getDocumentType());
            clientEntity.get().setNumberDocument(clientDto.getNumberDocument());
            clientEntity.get().setNumberPhone(clientDto.getNumberPhone());
            clientEntity.get().setMovil(clientDto.getMovil());
            clientEntity.get().setEmail(clientDto.getEmail());
            clientEntity.get().setAddress(clientDto.getAddress());
            clientEntity.get().setCity(clientDto.getCity());
            clientEntity.get().setStatus(clientDto.getStatus());
            clientRepository.save(clientEntity.get());

            return MapperObjects.clientEntityToClientDto(MapperObjects.clientDtoToClientEntity(clientDto));

        } else {
            throw new Exception("El producto no existe");

        }
    }*/
        @Override
        public void removeClient(Long id) throws Exception{
            Optional<ClientEntity> clientEntity = clientRepository.findById(id);

            if (clientEntity.isPresent()) {
                clientEntity.get().setStatus(0);
                clientRepository.save(clientEntity.get());
            } else {
                throw new BadRequestException("él cliente no existe en base de datos");
            }
        }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

}








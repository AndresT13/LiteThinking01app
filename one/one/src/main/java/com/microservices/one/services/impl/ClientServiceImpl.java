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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public ClientDto findById(Long id) {
        return MapperObjects.clientEntityToClientDto(clientRepository.findById(id).orElse(null));
    }


    @Override
    public ClientDto getNumberDocument(String numberDocument) {
        Optional<ClientEntity> clientEntity = clientRepository.findByNumberDocument(numberDocument);
        ClientDto clientDto = null;
        if (clientEntity.isPresent() && clientEntity.equals(Constants.DOCUMENT))
            clientDto = MapperObjects.clientEntityToClientDto(clientEntity.get());
           return clientDto;

    }


    @Override
    public ClientDto addClient(ClientDto clientDto) {
        ClientEntity client = ClientEntity.builder()
                .documentType(clientDto.getDocumentType())
                .numberDocument(clientDto.getNumberDocument())
                .firstName(clientDto.getFirstName())
                .secondName(clientDto.getSecondName())
                .secondFirstName(clientDto.getSecondFirstName())
                .secondLastName(clientDto.getSecondLastName())
                .numberPhone(clientDto.getNumberPhone())
                .movil(clientDto.getMovil())
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .city(clientDto.getCity())
                .status(clientDto.getStatus())
                .build();

        return MapperObjects.clientEntityToClientDto(clientRepository.save(client));
    }


    @Override
    public ClientDto updateProduct(Long id, ClientDto clientDto)  throws Exception {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        if (clientEntity.isPresent()) {
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

            return MapperObjects.clientEntityToClientDto(MapperObjects.clientDtoToproductEntity(clientDto));

        } else {
            throw new Exception("El producto no existe");

        }
    }
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








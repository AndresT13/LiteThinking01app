package com.microservices.one.utils;


import com.microservices.one.models.entities.ClientEntity;
import com.microservices.one.models.dto.ClientDto;

import java.util.Optional;

public interface MapperObjects {

    public static ClientDto clientEntityToClientDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .firstName(clientEntity.getFirstName())
                .secondName(clientEntity.getSecondName())
                .secondFirstName(clientEntity.getSecondFirstName())
                .secondLastName(clientEntity.getSecondLastName())
                .documentType(clientEntity.getDocumentType())
                .numberDocument(clientEntity.getNumberDocument())
                .numberPhone(clientEntity.getNumberPhone())
                .movil(clientEntity.getMovil())
                .email(clientEntity.getEmail())
                .address(clientEntity.getAddress())
                .status(clientEntity.getStatus())
                .build();
    }



    public static ClientEntity clientDtoToproductEntity(ClientDto clientDto) {
        return ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .secondName(clientDto.getSecondName())
                .secondFirstName(clientDto.getSecondFirstName())
                .secondLastName(clientDto.getSecondLastName())
                .documentType(clientDto.getDocumentType())
                .numberDocument(clientDto.getNumberDocument())
                .numberPhone(clientDto.getNumberPhone())
                .movil(clientDto.getMovil())
                .email(clientDto.getEmail())
                .address(clientDto.getAddress())
                .status(clientDto.getStatus())
                .build();

    }
}
package com.microservices.one.models.mock;

import com.microservices.one.models.dto.ClientDto;

public class ClientMock {

    private ClientMock(){
    }

    public static ClientDto getClientMock(){

        return ClientDto.builder()
                .id(1L)
                .firstName("Jhon")
                .secondName("A.")
                .secondFirstName("W.")
                .secondLastName("Doe")
                .documentType("CC")
                .numberDocument("12345678")
                .numberPhone(87999654)
                .movil(355258143)
                .address("Direccion Cliente Mock")
                .email("jhon.doe@email.ds")
                .city("Villavicencio")
                .status(1)
                .build();

    }
}

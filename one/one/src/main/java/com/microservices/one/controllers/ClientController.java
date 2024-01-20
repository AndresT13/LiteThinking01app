package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.mock.ClientMock;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IClientService;
import com.microservices.one.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;

import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    public ResponseEntity<?> getClients() {
        List<ClientDto> getList = clientService.listClient();
        if (getList == null || getList.isEmpty()) {
            throw new ResourceNotFoundException("clientes");
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);

    }

    @GetMapping(path="/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClient(@PathVariable Long id) {

        ClientDto cliente = clientService.findById(id);

        if (cliente == null) {
            throw new ResourceNotFoundException("cliente", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ClientDto.builder()
                                .id(cliente.getId())
                                .firstName(cliente.getFirstName())
                                .secondName(cliente.getSecondName())
                                .secondFirstName(cliente.getSecondFirstName())
                                .secondLastName(cliente.getSecondLastName())
                                .documentType(cliente.getDocumentType())
                                .numberDocument(cliente.getNumberDocument())
                                .numberPhone(cliente.getNumberPhone())
                                .movil(cliente.getMovil())
                                .email(cliente.getEmail())
                                .address(cliente.getAddress())
                                .city(cliente.getCity())
                                .status(cliente.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
             }


    @GetMapping(path="/document/{numberDocument}")
    public ResponseEntity<?> getNumberDocument(@PathVariable String numberDocument) {
        List<ClientDto> getList = clientService.listClient();
        ClientDto cliente = clientService.getNumberDocument(numberDocument);

        if (Constants.DOCUMENT.equals(numberDocument)){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                    .message("")
                    .object(ClientMock.getClientMock())
                    .build(),
                    HttpStatus.OK);

        }
        if (cliente == null) {
            throw new ResourceNotFoundException("cliente", "número de documento: ", numberDocument);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ClientDto.builder()
                                .id(cliente.getId())
                                .firstName(cliente.getFirstName())
                                .secondName(cliente.getSecondName())
                                .secondFirstName(cliente.getSecondFirstName())
                                .secondLastName(cliente.getSecondLastName())
                                .documentType(cliente.getDocumentType())
                                .numberDocument(cliente.getDocumentType())
                                .numberPhone(cliente.getNumberPhone())
                                .movil(cliente.getMovil())
                                .email(cliente.getEmail())
                                .address(cliente.getAddress())
                                .city(cliente.getCity())
                                .status(cliente.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
@PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDto client) {

    ClientDto clienteSave = null;
        try {
            clienteSave = clientService.addClient(client);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(ClientDto.builder()
                            .id(clienteSave.getId())
                            .firstName(clienteSave.getFirstName())
                            .secondName(clienteSave.getSecondName())
                            .secondFirstName(clienteSave.getSecondFirstName())
                            .secondLastName(clienteSave.getSecondLastName())
                            .documentType(clienteSave.getDocumentType())
                            .numberDocument(clienteSave.getNumberDocument())
                            .numberPhone(clienteSave.getNumberPhone())
                            .movil(clienteSave.getMovil())
                            .email(clienteSave.getEmail())
                            .address(clienteSave.getAddress())
                            .city(clienteSave.getCity())
                            .status(clienteSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
}


    @PutMapping(path = "/update/{numberDocument}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto, @PathVariable("numberDocument") String numberDocument) {
        ClientDto clientUpdate = null;

        try {

            clientDto.setNumberDocument(numberDocument);
            clientUpdate = clientService.addClient(clientDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(ClientDto.builder()
                            .id(clientUpdate.getId())
                            .firstName(clientUpdate.getFirstName())
                            .secondName(clientUpdate.getSecondName())
                            .secondFirstName(clientUpdate.getSecondFirstName())
                            .secondLastName(clientUpdate.getSecondLastName())
                            .documentType(clientUpdate.getDocumentType())
                            .numberDocument(clientUpdate.getNumberDocument())
                            .numberPhone(clientUpdate.getNumberPhone())
                            .movil(clientUpdate.getMovil())
                            .email(clientUpdate.getEmail())
                            .address(clientUpdate.getAddress())
                            .city(clientUpdate.getCity())
                            .status(clientUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<?> removeClient(@PathVariable Long id) throws Exception {

        try {
            ClientDto client = clientService.findById(id);
            clientService.removeClient(client.getId());
            return new ResponseEntity<>(client, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }



}

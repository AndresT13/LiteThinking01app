package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.entities.ClientEntity;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.ClientService;
import com.microservices.one.utils.Constants;
import com.microservices.one.utils.MapperObjects;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;

import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class ClientController {



    private final ClientService clientService;

    public ClientController(ClientService clientService) {
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
                                .firstName(cliente.getFirstName())
                                .secondName(cliente.getSecondName())
                                .secondFirstName(cliente.getSecondFirstName())
                                .secondLastName(cliente.getSecondLastName())
                                .documentType(cliente.getDocumentType())
                                .numberDocument(Constants.DOCUMENT)
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


    @RequestMapping(name="/document/{numberDocument}", method = RequestMethod.GET)
    public ResponseEntity<?> getNumberDocument(@PathVariable String numberDocument) {
        ClientDto cliente = clientService.getNumberDocument(numberDocument);

        if (cliente == null) {
            throw new ResourceNotFoundException("cliente", "número de documento: ", numberDocument);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ClientDto.builder()
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
    public ResponseEntity<?> createClient(@RequestParam @Valid @NotNull ClientDto clientDto) {

        ClientEntity clienteSave = null;
        try {
            clienteSave = MapperObjects.clientDtoToproductEntity(clientService.addClient(clientDto));
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(ClientDto.builder()
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
            throw  new BadRequestException(exDt.getMessage());
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@RequestBody @Valid ClientDto clientDto, @PathVariable Long id) throws Exception {
        ClientDto clientUpdate = null;

try {
    if (clientService.existsById(id)) {
        clientDto.setId(id);
        clientUpdate = clientService.addClient(clientDto);
        return new ResponseEntity<>(MessageResponse.builder()
                .message("Actualizado correctamente")
                .object(ClientDto.builder()
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
            } else {
                throw new ResourceNotFoundException("CLiente", "id", id);
            }
        }
            catch(DataAccessException exDt){
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

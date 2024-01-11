package com.microservices.one.controllers;

import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> getClients() {
        return (ResponseEntity<List<ClientDto>>) clientService.listClient();
    }

    @GetMapping("{id}")
    public List<ClientDto> getClient(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/{numberDocument}")
    public ResponseEntity<?> getNumberDocument(@PathVariable String numberDocument) {
        return clientService.getNumberDocument(numberDocument);
    }

    @PostMapping("/{numberDocument}")
    public ResponseEntity<?> addClient(@RequestBody ClientDto productDto) {
        return clientService.addClient(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto, @PathVariable Long id) {
        return clientService.updateProduct(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeClient(@RequestParam Long idProduct) {
        try {
            clientService.removeClient(idProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getCause(), HttpStatus.CONFLICT);

        }


    }

}

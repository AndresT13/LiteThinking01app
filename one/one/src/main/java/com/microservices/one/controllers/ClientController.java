package com.microservices.one.controllers;

import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.services.ClientService;
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
    public ResponseEntity<Object> getClients() {
         return ResponseEntity.ok(clientService.listClient());
    }

    @GetMapping("{id}")
    public List<ClientDto> getClient(@PathVariable Long id) {
        return (List<ClientDto>) clientService.findById(id);
    }

    @GetMapping("/{numberDocument}")
    public ClientDto getNumberDocument(@PathVariable String numberDocument) {
        return clientService.getNumberDocument(numberDocument);
    }

    @PutMapping("/{id}")
    public ClientDto updateClient(@RequestBody ClientDto clientDto, @PathVariable Long id) throws Exception {
        return clientService.updateProduct(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@RequestParam Long idProduct) throws Exception {
         clientService.removeClient(idProduct);
    }

}

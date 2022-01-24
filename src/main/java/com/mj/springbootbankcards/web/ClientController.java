package com.mj.springbootbankcards.web;

import com.mj.springbootbankcards.model.Client;
import com.mj.springbootbankcards.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable int id) {
        return ResponseEntity.of(clientService.findById(id));
    }

    @GetMapping("/{id}/with-cards")
    public ResponseEntity<Client> getWithCards(@PathVariable int id) {
        return ResponseEntity.of(clientService.findWithCards(id));
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client created = clientService.save(client);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("clients/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Client client, @PathVariable int id) {
        if (client.isNew()) {
            client.setId(id);
        } else if (client.getId() != id) {
            throw new IllegalArgumentException(client + " must has id=" + id);
        }
        clientService.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        clientService.deleteById(id);
    }
}

package com.mj.springbootbankcards.web;

import com.mj.springbootbankcards.model.Client;
import com.mj.springbootbankcards.service.ClientService;
import com.mj.springbootbankcards.to.ClientWithCardsTo;
import com.mj.springbootbankcards.to.ClientTo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ClientTo> get(@PathVariable int id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(modelMapper.map(client, ClientTo.class));
    }

    @GetMapping("/{id}/with-cards")
    public ResponseEntity<ClientWithCardsTo> getWithCards(@PathVariable int id) {
        Client client = clientService.findWithCards(id);
        return ResponseEntity.ok(modelMapper.map(client, ClientWithCardsTo.class));
    }

    @GetMapping
    public List<ClientTo> getAll() {
        List<Client> clients = clientService.findAll();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientTo.class))
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientTo> create(@Valid @RequestBody Client client) {
        ClientTo created = modelMapper.map(clientService.save(client), ClientTo.class);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("clients/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Client client, @PathVariable int id) {
        clientService.update(client, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        clientService.deleteById(id);
    }
}

package com.mj.springbootbankcards.web;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.model.Card;
import com.mj.springbootbankcards.model.Client;
import com.mj.springbootbankcards.service.BankCardTypeService;
import com.mj.springbootbankcards.service.CardService;
import com.mj.springbootbankcards.service.ClientService;
import com.mj.springbootbankcards.to.CardSaveTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clients/{clientId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private BankCardTypeService bankCardTypeService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Card> getAll(@PathVariable int clientId) {
        return cardService.findCardsByClientId(clientId);
    }

    @GetMapping("/active")
    public List<Card> getAllActive(@PathVariable int clientId) {
        return cardService.findCardsByClientIdAndLockedIsFalse(clientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> get(@PathVariable int clientId,
                                    @PathVariable int id) {
        return ResponseEntity.of(cardService.findByIdAndClientId(id, clientId));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> create(@RequestBody CardSaveTo cardSaveTo,
                                       @PathVariable int clientId,
                                       @RequestParam int bankCardTypeId) {
        Optional<Client> client = clientService.findById(clientId);
        Optional<BankCardType> bankCardType = bankCardTypeService.findById(clientId);
        Card created = cardService.save(cardSaveTo, client.get(), bankCardType.get());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/clients/" + clientId + "/cards/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{id}/block")
    public ResponseEntity<Card> block(@PathVariable int clientId,
                                      @PathVariable int id) {
        cardService.blockCard(id, clientId);
        Card blocked = cardService.findByIdAndClientId(id, clientId).get();
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/clients/" + clientId + "/cards/{id}")
                .buildAndExpand(blocked.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(blocked);
    }
}

package com.mj.springbootbankcards.web;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.model.Card;
import com.mj.springbootbankcards.model.Client;
import com.mj.springbootbankcards.service.BankCardTypeService;
import com.mj.springbootbankcards.service.CardService;
import com.mj.springbootbankcards.service.ClientService;
import com.mj.springbootbankcards.to.CardSaveTo;
import com.mj.springbootbankcards.to.CardWithTypeTo;
import com.mj.springbootbankcards.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients/{clientId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private BankCardTypeService bankCardTypeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CardWithTypeTo> getAll(@PathVariable int clientId) {
        log.info("get all cards for clientId {}", clientId);
        return MapperUtil.convertList(cardService.getCards(clientId), card -> modelMapper.map(card, CardWithTypeTo.class));
    }

    @GetMapping("/active")
    public List<CardWithTypeTo> getAllActive(@PathVariable int clientId) {
        log.info("get all active cards for clientId {}", clientId);
        return MapperUtil.convertList(cardService.getActiveCards(clientId), card -> modelMapper.map(card, CardWithTypeTo.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardWithTypeTo> get(@PathVariable int clientId,
                                              @PathVariable int id) {
        log.info("get cardId {} for clientId {}", id, clientId);
        Card card = cardService.getCard(id, clientId);
        return ResponseEntity.ok(modelMapper.map(card, CardWithTypeTo.class));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardWithTypeTo> create(@Valid @RequestBody CardSaveTo cardSaveTo,
                                                 @PathVariable int clientId,
                                                 @RequestParam int bankCardTypeId) {
        log.info("create card {} for clientId {} with bankCardTypeId {}", cardSaveTo, clientId, bankCardTypeId);
        Client client = clientService.findById(clientId);
        BankCardType bankCardType = bankCardTypeService.get(bankCardTypeId);
        Card created = cardService.save(modelMapper.map(cardSaveTo, Card.class), client, bankCardType);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/clients/" + clientId + "/cards/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(modelMapper.map(created, CardWithTypeTo.class));
    }

    @GetMapping(value = "/{id}/block")
    public ResponseEntity<CardWithTypeTo> block(@PathVariable int clientId,
                                                @PathVariable int id) {
        log.info("block cardId {} for clientId {}", id, clientId);
        cardService.blockCard(id, clientId);
        Card blocked = cardService.getCard(id, clientId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/clients/" + clientId + "/cards/{id}")
                .buildAndExpand(blocked.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(modelMapper.map(blocked, CardWithTypeTo.class));
    }
}

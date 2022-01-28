package com.mj.springbootbankcards.web;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.service.BankCardTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/bank-card-types", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BankCardTypesController {

    @Autowired
    private BankCardTypeService bankCardTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<BankCardType> get(@PathVariable int id) {
        log.info("get bankCardTypeId {}", id);
        return ResponseEntity.ok(bankCardTypeService.get(id));
    }

    @GetMapping
    public List<BankCardType> getAll() {
        log.info("get all bankCardTypes");
        return bankCardTypeService.getAll();
    }

    @GetMapping("/debit")
    public List<BankCardType> getDebit() {
        log.info("get debit bankCardTypes");
        return bankCardTypeService.getDebit();
    }

    @GetMapping("/credit")
    public List<BankCardType> getCredit() {
        log.info("get credit bankCardTypes");
        return bankCardTypeService.getCredit();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankCardType> create(@Valid @RequestBody BankCardType bankCardType) {
        log.info("create {}", bankCardType);
        BankCardType created = bankCardTypeService.add(bankCardType);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("clients/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody BankCardType bankCardType, @PathVariable int id) {
        log.info("update {} with bankCardTypeId {}", bankCardType, id);
        bankCardTypeService.update(bankCardType, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete bankCardTypeId {}", id);
        bankCardTypeService.delete(id);
    }
}
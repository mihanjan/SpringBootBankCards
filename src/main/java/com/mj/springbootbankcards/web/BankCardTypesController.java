package com.mj.springbootbankcards.web;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.service.BankCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/bank-card-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankCardTypesController {

    @Autowired
    private BankCardTypeService bankCardTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<BankCardType> get(@PathVariable int id) {
        return ResponseEntity.of(bankCardTypeService.findById(id));
    }

    @GetMapping
    public List<BankCardType> getAll() {
        return bankCardTypeService.findAll();
    }

    @GetMapping("/debit")
    public List<BankCardType> getDebit() {
        return bankCardTypeService.findDebitBankCardTypes();
    }

    @GetMapping("/credit")
    public List<BankCardType> getCredit() {
        return bankCardTypeService.findCreditBankCardTypes();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankCardType> create(@RequestBody BankCardType bankCardType) {
        BankCardType created = bankCardTypeService.save(bankCardType);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("clients/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BankCardType bankCardType, @PathVariable int id) {
        if (bankCardType.isNew()) {
            bankCardType.setId(id);
        } else if (bankCardType.getId() != id) {
            throw new IllegalArgumentException(bankCardType + " must has id=" + id);
        }
        bankCardTypeService.save(bankCardType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        bankCardTypeService.deleteById(id);
    }
}
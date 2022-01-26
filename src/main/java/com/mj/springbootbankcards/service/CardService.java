package com.mj.springbootbankcards.service;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.model.Card;
import com.mj.springbootbankcards.model.Client;
import com.mj.springbootbankcards.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public Card save(Card card, Client client, BankCardType bankCardType) {
        card.setNumber(String.valueOf(Card.counterNumber.incrementAndGet()));
        card.setContractNumber(String.valueOf(Card.counterContractNumber.incrementAndGet()));
        card.setExpireDate(card.getOpenDate().plusYears(bankCardType.getValidity()));
        card.setClient(client);
        card.setBankCardType(bankCardType);
        return repository.save(card);
    }

    public Card getCard(int id, int clientId) {
        return repository.findByIdAndClientId(id, clientId).get();
    }

    public List<Card> getCards(int clientId) {
        return repository.findCardsByClientIdWithType(clientId);
    }

    public List<Card> getActiveCards(int clientId) {
        return repository.findCardsByClientIdAndLockedIsFalse(clientId);
    }

    public void blockCard(int id, int clientId) {
        repository.blockCard(id, clientId);
    }
}
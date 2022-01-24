package com.mj.springbootbankcards.service;

import com.mj.springbootbankcards.model.Card;
import com.mj.springbootbankcards.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public Card save(Card card) {
        return repository.save(card);
    }

    public Optional<Card> findByIdAndClientId(int id, int clientId) {
        return repository.findByIdAndClientId(id, clientId);
    }

    public List<Card> findCardsByClientId(int clientId) {
        return repository.findCardsByClientId(clientId);
    }

    public List<Card> findCardsByClientIdAndLockedIsFalse(int clientId) {
        return repository.findCardsByClientIdAndLockedIsFalse(clientId);
    }

    public int blockCard(int id, int clientId) {
        return repository.blockCard(id, clientId);
    }
}
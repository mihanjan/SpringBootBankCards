package com.mj.springbootbankcards.repository;

import com.mj.springbootbankcards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByIdAndClientId(int id, int client);

    List<Card> findCardsByClientId(int clientId);

    List<Card> findCardsByClientIdAndLockedIsFalse(int clientId);

    @Transactional
    @Modifying
    @Query("UPDATE Card c SET c.locked = TRUE, c.lockDate = CURRENT_DATE WHERE c.id = :id AND c.client.id = :clientId")
    int blockCard(int id, int clientId);
}

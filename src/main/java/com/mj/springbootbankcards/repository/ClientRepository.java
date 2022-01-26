package com.mj.springbootbankcards.repository;

import com.mj.springbootbankcards.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @EntityGraph(value = "Client.cards", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT c FROM Client c WHERE c.id = :id")
    Optional<Client> findWithCards(int id);
}

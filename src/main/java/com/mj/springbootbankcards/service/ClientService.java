package com.mj.springbootbankcards.service;

import com.mj.springbootbankcards.model.Client;
import com.mj.springbootbankcards.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findWithCards(int id) {
        return clientRepository.findWithCards(id);
    }

    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}

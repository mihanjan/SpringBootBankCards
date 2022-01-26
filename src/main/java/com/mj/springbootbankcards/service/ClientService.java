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

    public void update(Client client, int id) {
        if (client.isNew()) {
            client.setId(id);
        } else if (client.getId() != id) {
            throw new IllegalArgumentException(client + " must has id=" + id);
        }
        clientRepository.save(client);
    }

    public Client findById(int id) {
        return clientRepository.findById(id).get();
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findWithCards(int id) {
        return clientRepository.findWithCards(id).get();
    }

    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}

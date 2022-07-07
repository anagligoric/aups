package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Client;
import com.example.aups.repositories.ClientRepository;
import com.example.aups.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Client with id " + id + " does not exist."));
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }


    public Client update(Long id, Client client) {
        if (clientRepository.findById(id).isEmpty()) {
            throw new CustomException("Client with id " + id + " does not exist.");
        }
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        if (clientRepository.findById(id).isEmpty()) {
            throw new CustomException("Client with id " + id + " does not exist.");
        }
        clientRepository.deleteById(id);
    }
}


package com.example.demo.services;

import com.example.demo.dataTransferObjects.TokenRO;
import com.example.demo.dataTransferObjects.LoginRO;
import com.example.demo.models.Client;
import com.example.demo.dataTransferObjects.RegisterRO;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public TokenRO registerNewClient(RegisterRO register) {
        Optional<Client> clientEmail = clientRepository.findClientByEmail(register.getEmail());
        if (clientEmail.isPresent()) {
            throw new IllegalStateException();
        }
        Client client = new Client(
                register.getNickName(),
                register.getEmail(),
                register.getPassword(),
                register.getBio(),
                register.getDob()
        );
        clientRepository.save(client);
        return new TokenRO("FakeToken");
    }
    public TokenRO loginClient(LoginRO login) {
        Client client = clientRepository.findClientByEmailAndPassword(
                login.getEmail(),
                login.getPassword())
                .orElseThrow();
        return new TokenRO("FakeToken");
    }
}

package com.example.demo.services;

import com.example.demo.dataTransferObjects.TokenRO;
import com.example.demo.dataTransferObjects.LoginRO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.mappers.ClientMapper;
import com.example.demo.models.Client;
import com.example.demo.dataTransferObjects.RegisterDTO;
import com.example.demo.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public TokenRO registerNewClient(RegisterDTO register) {
        Optional<Client> clientEmail = clientRepository.findClientByEmail(register.email());
        if (clientEmail.isPresent()) {
            throw new IllegalStateException();
        }
        Client client = clientMapper.apply(register);
        clientRepository.save(client);
        return new TokenRO("FakeToken");
    }
    public TokenRO loginClient(LoginRO login) {
        Optional<Client> opt = clientRepository.findClientByEmailAndPassword(
                login.getEmail(),
                login.getPassword());
        if (opt.isEmpty()) {
            throw new BadRequestException("Connection failed");
        }
        logger.info("Client:" + opt.get().toString() + " connection succesful");
        return new TokenRO("FakeToken");
    }
}

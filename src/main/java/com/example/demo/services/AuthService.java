package com.example.demo.services;

import com.example.demo.dataTransferObjects.TokenRO;
import com.example.demo.dataTransferObjects.LoginRO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.filters.JwtService;
import com.example.demo.mappers.ClientMapper;
import com.example.demo.models.Client;
import com.example.demo.dataTransferObjects.RegisterDTO;
import com.example.demo.models.ClientDetails;
import com.example.demo.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(ClientRepository clientRepository, ClientMapper clientMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public TokenRO registerNewClient(RegisterDTO register) {
        Optional<Client> opt = clientRepository.findClientByEmail(register.email());
        if (opt.isPresent()) {
            throw new ConflictException("Email already used");
        }
        Client client = clientMapper.apply(register);
        String encodedPws = passwordEncoder.encode(register.password());
        logger.info("Encoded password: " + encodedPws + " for user: " + client.getNickName());
        client.setPassword(encodedPws);
        clientRepository.save(client);
        return new TokenRO(jwtService.generateToken(new ClientDetails(client)));
    }
    public TokenRO loginClient(LoginRO login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword())
        );
        Optional<Client> opt = clientRepository.findClientByEmail(login.getEmail());
        if (opt.isEmpty()) {
            throw new BadRequestException("Connection failed");
        }
        logger.info("Client:" + opt.get() + " connection succesful");
        return new TokenRO(jwtService.generateToken(new ClientDetails(opt.get())));
    }
}

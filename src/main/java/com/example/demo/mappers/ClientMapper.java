package com.example.demo.mappers;

import com.example.demo.dataTransferObjects.RegisterDTO;
import com.example.demo.models.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClientMapper implements Function<RegisterDTO, Client> {
    @Override
    public Client apply(RegisterDTO registerDTO) {
        return new Client(
                registerDTO.nickName(),
                registerDTO.email(),
                null,
                registerDTO.bio(),
                registerDTO.dob());
    }
}

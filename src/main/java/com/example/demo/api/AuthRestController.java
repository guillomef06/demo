package com.example.demo.api;

import com.example.demo.dataTransferObjects.TokenRO;
import com.example.demo.dataTransferObjects.LoginRO;
import com.example.demo.dataTransferObjects.RegisterDTO;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth/")
@CrossOrigin("*")
public class AuthRestController {
    private final ClientService clientService;
    @Autowired
    public AuthRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("register")
    public TokenRO register(@RequestBody RegisterDTO register) {
        return this.clientService.registerNewClient(register);
    }

    @PutMapping("login")
    public TokenRO login(@RequestBody LoginRO login) {
        return this.clientService.loginClient(login);
    }
}

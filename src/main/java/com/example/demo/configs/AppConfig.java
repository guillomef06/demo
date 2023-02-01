package com.example.demo.configs;

import com.example.demo.models.Client;
import com.example.demo.models.Pix;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AppConfig {

    private final ClientRepository clientRepository;

    private final PixRepository pixRepository;

    @Autowired
    public AppConfig(ClientRepository clientRepository, PixRepository pixRepository) {
        this.clientRepository = clientRepository;
        this.pixRepository = pixRepository;
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Client john = new Client(
                    "John",
                    "john@email.com",
                    "john91",
                    "Une bio de folie",
                    LocalDate.of( 1991, Month.JUNE, 16)

            );
            Client alex = new Client(
                    "Alex",
                    "alex@email.com",
                    "alex92",
                    "Super bio",
                    LocalDate.of( 1992, Month.AUGUST, 16)
            );
            clientRepository.saveAll(List.of(john, alex));
            Pix firstPix = new Pix(
                    john,
                    "Super titre",
                    "Et ouais c'est Marseille bébé",
                    "Marseille",
                    "https://cdn.pixabay.com/photo/2015/05/31/16/03/teddy-bear-792273_1280.jpg"
            );
            Pix secondPix = new Pix(
                    john,
                    "Un mega titre",
                    "Pas besoin de commentaire",
                    "Paris",
                    "https://cdn.pixabay.com/photo/2015/05/31/16/03/teddy-bear-792273_1280.jpg"
            );
            Pix thirdPix = new Pix(
                    alex,
                    "Un dernier pour la route",
                    "Indice c'est pas Marseille",
                    "Madrid",
                    "https://cdn.pixabay.com/photo/2015/05/31/16/03/teddy-bear-792273_1280.jpg"
            );
            pixRepository.saveAll(List.of(firstPix, secondPix, thirdPix));
        };
    }
}

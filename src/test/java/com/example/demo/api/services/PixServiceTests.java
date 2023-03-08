package com.example.demo.api.services;

import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.mappers.PixDTOMapper;
import com.example.demo.mappers.PixMapper;
import com.example.demo.models.Client;
import com.example.demo.models.Pix;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.PixRepository;
import com.example.demo.services.PixService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PixServiceTests {

    @Mock
    private PixRepository pixRepository;

    @InjectMocks
    private PixService pixService;

    @Test
    public void getPixById() {
        //ARRANGE
        PixDTO pixDto = new PixDTO(
                null,
                1L,
                "Test",
                "Caption test",
                "Location",
                "http://someUrl.com/123.jpg",
                new Date(),
                0);
        Pix pix = new Pix("Test", "Caption test", "Location", "http://someUrl.com/123.jpg");
        pix.setId(1L);
        when(pixRepository.findById(1L)).thenReturn(Optional.ofNullable(pix));

        //ACT
        PixDTO saved = pixService.getPixById(1L);

        //ASSERT
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(saved.title(), "Test");
    }
}

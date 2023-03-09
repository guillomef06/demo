package com.example.demo.api.services;

import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.services.PixService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
public class PixServiceTests {

    @MockBean
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

        //ACT
        when(pixService.getPixById(1L)).thenReturn(pixDto);
        PixDTO saved = pixService.getPixById(1L);
        //ASSERT
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(saved.title(), "Test");
    }
}

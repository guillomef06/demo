package com.example.demo.api.controllers;

import com.example.demo.api.PixRestController;
import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.services.PixService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PixRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PixRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PixService pixService;

    @Autowired
    private ObjectMapper objectMapper;

    private PixDTO pixDTO;

    @BeforeEach
    public void init() {
        pixDTO = new PixDTO(1L,
                1L,
                "Test",
                "Caption test",
                "Location",
                "http://someUrl.com/1234.jpg",
                new Date(),
                0);
    }

    @Test
    public void createPixAndCreated() throws Exception {
        given(pixService.createPix(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions resp = mockMvc.perform(post("/api/v1/pix")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(pixDTO)));

        resp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}

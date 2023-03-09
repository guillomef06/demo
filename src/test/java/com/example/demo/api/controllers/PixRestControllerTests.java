package com.example.demo.api.controllers;

import com.example.demo.api.PixRestController;
import com.example.demo.configs.SecurityConfig;
import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.filters.AuthFilter;
import com.example.demo.services.PixService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PixRestController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecurityConfig.class, AuthFilter.class}))
@AutoConfigureMockMvc(addFilters = false)
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

        ResultActions resp = mockMvc.perform(post("/api/v1/pix/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(pixDTO)));

        resp.andExpect(status().isCreated());
    }

    @Test
    public void getPixById() throws Exception {
        given(pixService.getPixById(1L)).willReturn(pixDTO);

        mockMvc.perform(get("/api/v1/pix/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(pixDTO.title()));
    }
}

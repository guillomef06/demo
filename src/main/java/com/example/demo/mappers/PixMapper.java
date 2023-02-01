package com.example.demo.mappers;

import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.models.Pix;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PixMapper implements Function<PixDTO, Pix> {
    @Override
    public Pix apply(PixDTO pixDTO) {
        return new Pix(pixDTO.title(),
                pixDTO.caption(),
                pixDTO.location(),
                pixDTO.imageUrl());
    }
}

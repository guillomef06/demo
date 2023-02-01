package com.example.demo.mappers;

import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.models.Pix;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PixDTOMapper implements Function<Pix, PixDTO> {
    @Override
    public PixDTO apply(Pix pix) {
        return new PixDTO(
                pix.getId(),
                pix.getClient().getId(),
                pix.getTitle(),
                pix.getCaption(),
                pix.getLocation(),
                pix.getImageUrl(),
                pix.getCreationDate(),
                pix.getLikes()
        );
    }
}

package com.example.demo.dataTransferObjects;

import java.util.Date;

public record PixDTO(
        Long id,
        Long clientId,
        String title,
        String caption,
        String location,
        String imageUrl,
        Date creationDate,
        Integer likes
) {

}

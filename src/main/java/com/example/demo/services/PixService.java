package com.example.demo.services;

import com.example.demo.mappers.PixDTOMapper;
import com.example.demo.models.Pix;
import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.repositories.PixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PixService {

    private final PixRepository pixRepository;

    private final PixDTOMapper pixDTOMapper;
    private final Logger logger = LoggerFactory.getLogger(PixService.class);

    @Autowired
    public PixService(PixRepository pixRepository, PixDTOMapper pixDTOMapper) {
        this.pixRepository = pixRepository;
        this.pixDTOMapper = pixDTOMapper;
    }

    public List<PixDTO> getAllPixs(Integer pageIndex) {
        PageRequest page = PageRequest.of(0 + pageIndex,5);
        List<Pix> pixs = this.pixRepository.findByOrderByIdDesc(page);
        if (pixs.isEmpty()) {
            logger.info("No pixs");
        } else {
            return pixs.stream().map(pixDTOMapper).collect(Collectors.toList());
        }
        return null;
    }

    public PixDTO getPixById(Long id) {
        Optional<Pix> opt = this.pixRepository.findById(id);
        if (opt.isEmpty()) {
            logger.info("Pix not found");
        } else {
            return pixDTOMapper.apply(opt.get());
        }
        return null;
    }

    public Pix likePixById(Long id, Pix pix) {
        if (pix.getId().equals(id)) {
            logger.info("pix id: " + id + " has been updated");
            this.pixRepository.save(pix);
        }
        return pix;
    }

    public Pix createPix(Pix pix) {
        if (pix.getTitle() != null && pix.getCaption() != null && pix.getImageUrl() != null && pix.getLocation() != null) {
            pix.setCreationDate(new Date());
            this.pixRepository.save(pix);
        }
        return pix;
    }
}

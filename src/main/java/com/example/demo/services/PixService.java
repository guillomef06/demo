package com.example.demo.services;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mappers.PixDTOMapper;
import com.example.demo.mappers.PixMapper;
import com.example.demo.models.Client;
import com.example.demo.models.Pix;
import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.PixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PixService {

    private final PixRepository pixRepository;

    private final ClientRepository clientRepository;

    private final PixDTOMapper pixDTOMapper;

    private final PixMapper pixMapper;

    private final Logger logger = LoggerFactory.getLogger(PixService.class);

    @Autowired
    public PixService(PixRepository pixRepository, ClientRepository clientRepository, PixDTOMapper pixDTOMapper, PixMapper pixMapper) {
        this.pixRepository = pixRepository;
        this.clientRepository = clientRepository;
        this.pixDTOMapper = pixDTOMapper;
        this.pixMapper = pixMapper;
    }

    public List<PixDTO> getAllPixs(Pageable page) {
        List<Pix> pixs = this.pixRepository.findByOrderByIdDesc(page);
        if (pixs.isEmpty()) {
            logger.info("No pixs");
            throw new NotFoundException("No pixs !");
        }
        return pixs.stream().map(pixDTOMapper).collect(Collectors.toList());
    }

    public PixDTO getPixById(Long id) {
        Optional<Pix> opt = this.pixRepository.findById(id);
        if (opt.isPresent()) {
            return pixDTOMapper.apply(opt.get());
        }
        logger.info("Pix not found");
        throw new NotFoundException("Pix not found");
    }

    public void likePixById(Long id) {
        Optional<Pix> opt = pixRepository.findById(id);
        if (opt.isPresent()) {
            Integer likes = opt.get().getLikes();
            opt.get().setLikes(likes + 1);
            this.pixRepository.save(opt.get());
        }
        logger.info("Pix not found");
        throw new NotFoundException("Pix not found");
    }

    public PixDTO createPix(PixDTO pixDTO) {
        Optional<Client> opt = this.clientRepository.findById(pixDTO.clientId());
        if (opt.isPresent()) {
            Pix newPix = pixMapper.apply(pixDTO);
            newPix.setClient(opt.get());
            this.pixRepository.save(newPix);
            return pixDTO;
        }
        throw new BadRequestException("Could not find the user");
    }
}

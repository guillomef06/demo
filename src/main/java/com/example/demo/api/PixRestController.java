package com.example.demo.api;

import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.services.PixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pix/")
@CrossOrigin("*")
public class PixRestController {

    private final PixService pixService;

    Logger logger = LoggerFactory.getLogger(PixRestController.class);

    @Autowired
    public PixRestController(PixService pixService) { this.pixService = pixService; }

    @GetMapping("")
    public List<PixDTO> getAllPix(Pageable page) {
        return this.pixService.getAllPixs(page);
    }

    @GetMapping("{id}")
    public PixDTO getPixById(@PathVariable("id") Long id) {
        return this.pixService.getPixById(id);
    }

    @PutMapping("{id}")
    public void likePixById(@PathVariable("id") Long id) {
        this.pixService.likePixById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PixDTO createPix(@RequestBody PixDTO pixDTO) {
        return this.pixService.createPix(pixDTO);
    }
}

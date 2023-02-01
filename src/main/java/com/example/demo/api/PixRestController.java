package com.example.demo.api;

import com.example.demo.models.Pix;
import com.example.demo.dataTransferObjects.PixDTO;
import com.example.demo.services.PixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("page/{page}")
    public List<PixDTO> getAllPix(@PathVariable("page") Integer page) {
        return this.pixService.getAllPixs(page);
    }

    @GetMapping("{id}")
    public PixDTO getPixById(@PathVariable("id") Long id) {
        return this.pixService.getPixById(id);
    }

    @PutMapping("{id}")
    public Pix likePixById(@PathVariable("id") Long id, @RequestBody Pix pix) {
        return this.pixService.likePixById(id, pix);
    }

    @PostMapping("")
    public Pix createPix(@RequestBody Pix pix) {
        return this.pixService.createPix(pix);
    }
}

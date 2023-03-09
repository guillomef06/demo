package com.example.demo.api.repositories;

import com.example.demo.models.Pix;
import com.example.demo.repositories.PixRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PixRepositoryTests {

    private final PixRepository pixRepository;

    @Autowired
    public PixRepositoryTests(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @Test
    public void savePix() {
        //ARRANGE
        Pix pix = new Pix("Test","Caption test", "caption location", "http://somurl.123.jpg");

        //ACT
        Pix saved = pixRepository.save(pix);

        //ASSERT
        Assertions.assertNotNull(saved);
        Assertions.assertNotEquals(0, saved.getId());
    }

    @Test
    public void findAll() {
        //ARRANGE
        Pix pix1 = new Pix("Test1","Caption test", "caption location", "http://somurl.123.jpg");
        Pix pix2 = new Pix("Test2","Caption test", "caption location", "http://somurl.123.jpg");

        pixRepository.save(pix1);
        pixRepository.save(pix2);

        //ACT
        List<Pix> saved= pixRepository.findAll();

        //ASSERT
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(2, saved.size());
    }

    @Test
    public void findById() {
        //ARRANGE
        Pix pix1 = new Pix("Test1","Caption test", "caption location", "http://somurl.123.jpg");
        pixRepository.save(pix1);

        //ACT
        Pix saved = pixRepository.findById(pix1.getId()).get();

        //ASSERT
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(saved.getTitle(), pix1.getTitle());
    }
}

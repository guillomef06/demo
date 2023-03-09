package com.example.demo.repositories;

import com.example.demo.models.Pix;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {

    List<Pix> findByOrderByIdDesc(Pageable pageable);
}

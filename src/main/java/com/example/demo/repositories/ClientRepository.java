package com.example.demo.repositories;

import com.example.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    //@Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Client> findClientByEmail(String email);

    Optional<Client> findClientByNickName(String nickName);
}

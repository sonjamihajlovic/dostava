package com.example.demo.repository;

import com.example.demo.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {

    //Artikal getByNaziv(String naziv);
    Optional<Artikal> findById(Long id);
}

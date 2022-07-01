package com.example.demo.repository;

import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestoranRepository extends JpaRepository<Restoran, Long> {
    Restoran getByNaziv(String naziv);

   // Restoran getByLokacija(Long lokacijaId);

    List<Restoran> getByTipRestorana(String tip);

    Restoran getByLokacijaId(Long lokacijaId);
    Restoran getByAdresa(String adresa);
    Optional<Restoran> findById(Long id);
    void delete(Restoran restoran);
}

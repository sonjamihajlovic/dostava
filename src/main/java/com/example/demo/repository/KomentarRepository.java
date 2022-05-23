package com.example.demo.repository;

import com.example.demo.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    List<Komentar> getByRestoranId(Long id);
}

package com.example.demo.repository;

import com.example.demo.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
}

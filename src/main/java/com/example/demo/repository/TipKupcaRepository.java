package com.example.demo.repository;

import com.example.demo.entity.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipKupcaRepository extends JpaRepository<TipKupca, Long> {
    TipKupca findByIme(String ime);
}

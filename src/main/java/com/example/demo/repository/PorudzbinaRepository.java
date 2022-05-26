package com.example.demo.repository;

import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {

    public Set<Porudzbina> getByRestoran(Restoran rst);

    public Set<Porudzbina> getByKupac(Set<Porudzbina> svePorudzbine);

    public Set<Porudzbina> getByRestoran(Set<Porudzbina> porudzbine);
}

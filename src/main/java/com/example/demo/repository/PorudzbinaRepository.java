package com.example.demo.repository;

import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {

    public Set<Porudzbina> getByRestoran(Restoran rst);

    public Set<Porudzbina> getByKupac(Set<Porudzbina> svePorudzbine);

    public Set<Porudzbina> getByRestoran(Set<Porudzbina> porudzbine);

   public List<Porudzbina> findAllByStatus(Status status);

   public Optional<Porudzbina> findByUuid(UUID uuid);


}

package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal nadjiArtikal(Long id) {
        Optional<Artikal> artikal = artikalRepository.findById(id);
        if (artikal.isPresent())
            return artikal.get();

        return null;
    }

    public Artikal save(Artikal artikal) {
        return artikalRepository.save(artikal);
    }

    public void delete(Long id) {
        artikalRepository.deleteById(id);
    }

   /* public Artikal getByNaziv(String naziv) {
        Artikal artikal = artikalRepository.getByNaziv(naziv);
        return artikal;
    }*/
}

package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    public Menadzer save(Menadzer menadzer) {
        return menadzerRepository.save(menadzer);
    }

    public Artikal saveArtikal(Artikal artikal) {
        return artikalRepository.save(artikal);
    }

    public String dodajMenadzer(Korisnik korisnik){
        Menadzer m=new Menadzer(korisnik);
        menadzerRepository.save(m);
        return "Menadzer je sacuvan";
    }

    public Restoran nadjiRestoran(Korisnik korisnik) {
        Menadzer m= menadzerRepository.getById(korisnik.getId());
        return m.getRestoran();
    }

    public Set<Porudzbina> nadjiPorudzbine(Korisnik korisnik) {
        Menadzer m=menadzerRepository.getById(korisnik.getId());
        Set<Porudzbina> setp=porudzbinaRepository.getByRestoran(m.getRestoran());
        return setp;
    }
}

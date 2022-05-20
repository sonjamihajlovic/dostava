package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
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

    public Menadzer save(Menadzer menadzer) {
        return menadzerRepository.save(menadzer);
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

package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

  //  public String dodaj_menadzer(Korisnik korisnik) {
    //}

    public Menadzer save(Menadzer menadzer) {
        return menadzerRepository.save(menadzer);
    }

    public String dodajMenadzer(Korisnik korisnik){
        Menadzer m=new Menadzer(korisnik);
        menadzerRepository.save(m);
        return "Menadzer je sacuvan";
    }
}

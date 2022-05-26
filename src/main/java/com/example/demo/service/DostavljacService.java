package com.example.demo.service;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DostavljacService {

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public Dostavljac save(Dostavljac dostavljac) {
        return dostavljacRepository.save(dostavljac);
    }

    public Set<Porudzbina> izlistajPorudzbine1(Korisnik dostavljac) {
        Dostavljac dost=dostavljacRepository.getById(dostavljac.getId());
        Set<Porudzbina> setp1=porudzbinaRepository.getByRestoran(dost.getPorudzbine());
        return setp1;
    }
}

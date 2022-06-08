package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private TipKupcaService tipKupcaService;

    public Kupac save(Kupac kupac) {
        TipKupca tipKupca= tipKupcaService.findByName("REGULARNI");
        kupac.setTipkupca(tipKupca);
        return kupacRepository.save(kupac);
    }

    public Set<Porudzbina> izlistajPorudzbine(Korisnik kupac) {
        Kupac kup=kupacRepository.getById(kupac.getId());
        Set<Porudzbina> setp=porudzbinaRepository.getByKupac(kup.getSvePorudzbine());
        return kup.getSvePorudzbine();
    }
}

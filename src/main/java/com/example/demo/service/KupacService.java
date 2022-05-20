package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.TipKupca;
import com.example.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;


    @Autowired
    private TipKupcaService tipKupcaService;

    public Kupac save(Kupac kupac) {
        TipKupca tipKupca= tipKupcaService.findByName("REGULARNI");
        kupac.setTipkupca(tipKupca);
        return kupacRepository.save(kupac);
    }
}

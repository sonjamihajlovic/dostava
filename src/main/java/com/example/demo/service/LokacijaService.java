package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Lokacija;
import com.example.demo.entity.TipKupca;
import com.example.demo.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LokacijaService {

    @Autowired
    public LokacijaRepository lokacijaRepository;

    public Lokacija getLokacijaById(Long id){
       Optional <Lokacija> lokacija = lokacijaRepository.findById(id);
       if(lokacija.isPresent())
        return lokacija.get();
       return null;
    }


    public Lokacija save(Lokacija lokacija) { return lokacijaRepository.save(lokacija);
    }

    /*public Lokacija getByAdresa(String naziv) {
        return lokacijaRepository.getByAdresa(naziv);

    }*/


}

package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    public Restoran save(Restoran restoran) {
        return restoranRepository.save(restoran);
    }

   /* public List<Restoran> findAll(){
        return restoranRepository.findAll();
    }*/

    public List<Restoran> getAll() {
        return restoranRepository.findAll();
    }

    public Restoran getByNaziv(String naziv) {
        Restoran restoran =  restoranRepository.getByNaziv(naziv);
        return restoran;
    }

    public Restoran getByLokacija(Long lokacijaId) {
        Restoran restoran =  restoranRepository.getByLokacijaId(lokacijaId);
        return restoran;
    }

    public List<Restoran> getByTip(String tip) {
         return restoranRepository.getByTipRestorana(tip);
    }
}

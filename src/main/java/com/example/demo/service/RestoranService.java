package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private PorudzbinaService porudzbinaService;

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
/*
    public Restoran getByLokacija(Long lokacijaId) {
        Restoran restoran =  restoranRepository.getByLokacijaId(lokacijaId);
        return restoran;
    }*/

    public Restoran getByAdresa(String adresa)
    {
        return restoranRepository.getByAdresa(adresa);
    }

    public Restoran findOne(Long id) {
        Optional<Restoran> restoran = restoranRepository.findById(id);
        if (restoran.isPresent())
            return restoran.get();

        return null;
    }

    public List<Restoran> getByTip(String tip) {
         return restoranRepository.getByTipRestorana(tip);
    }

    public ResponseEntity<String> obrisiRestoran(HttpSession session, Long id) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if (logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava na brisanje restorana!", HttpStatus.FORBIDDEN);
        }

      /*  if (restoranRepository.findById(id) == null)
            return new ResponseEntity("Uneti restoran ne postoji", HttpStatus.BAD_REQUEST);
*/
        Restoran restoran = restoranRepository.findById(id).get();
        //komentar
        for(Komentar komentar : komentarService.findAll()) {
            if(komentar.getRestoran().equals(restoran)) {
                komentar.setRestoran(null);
                komentarService.save(komentar);
            }
        }

        //menadzer
        for(Menadzer menadzer : menadzerService.findAll()) {
            if(menadzer.getRestoran().equals(restoran)) {
                menadzer.setRestoran(null);
                menadzerService.save(menadzer);
            }
        }

        //porudzbina
        for(Porudzbina porudzbina : porudzbinaService.findAll()) {
            if(porudzbina.getRestoran().equals(restoran)) {
                porudzbina.setRestoran(null);
                porudzbinaService.save(porudzbina);
            }
        }

        restoranRepository.delete(restoran);
        return ResponseEntity.ok("Restoran uspesno obrisan");


    }












}

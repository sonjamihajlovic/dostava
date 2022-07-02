package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
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
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private ArtikalService artikalService;

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



    // BRISANJE ARTIKLA SA BRISANJEM IZ RESTORANA


    public void deleteArtikal(Artikal artikal) {
        artikalService.delete(artikal);
    }

    public boolean isArtikalURestoranu(Long id1, Long id2) { // id1 za artikal, 2 za restoran
        Restoran restoran = findRestoranById(id2);

        for (Artikal a : restoran.getArtikli()) {
            if(a.getId().equals(id1)){
                return true;
            }
        }
        return false;
    }

    public Restoran findRestoranById(Long id) {
        List<Restoran> restorani = restoranRepository.findAll();

        for (Restoran r : restorani) {
            if (r.getId().equals(id)) {
                return r;
            }
        }

        return null;
    }

    public ResponseEntity<String> removeArtikal(Long id, HttpSession session) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if (logovani.getUloga() != Uloga.MENADZER || logovani == null) {
            return new ResponseEntity("Nemate prava na brisanje restorana!", HttpStatus.FORBIDDEN);
        }
        Menadzer logovaniMenadzer = (Menadzer) logovani;
        Restoran restoran = logovaniMenadzer.getRestoran();

        for (Artikal artikal : restoran.getArtikli()) {
            if (artikal.getId().equals(id)) {
                restoran.getArtikli().remove(artikal);
                deleteArtikal(artikal); //ne znamo da li treba da se brise i da li ce raditi bez ovoga
                save(restoran);
                return ResponseEntity.ok("Uspesno obrisan artikal");
            }
        }

        return new ResponseEntity<>("Ne postoji artikal sa tim id-jem za restoran za koji je zaduzen ulogovani menadzer", HttpStatus.NOT_FOUND);
    }

    public Restoran findOneById(long id){
        Optional<Restoran> r = restoranRepository.findById(id);

        if(r.isPresent()){
            return r.get();
        }
        return null;
    }









}

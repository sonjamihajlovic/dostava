package com.example.demo.service;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    public Korisnik findOne(Long id){
        Optional<Korisnik> pronadjeniKorisnik = korisnikRepository.findById(id);
        if (pronadjeniKorisnik.isPresent())
            return pronadjeniKorisnik.get();

        return null;
    }

    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik save(Korisnik korisnik, Uloga uloga) {
        if(uloga.equals(Uloga.MENADZER)) {
            menadzerRepository.save((Menadzer) korisnik);
        }else if(uloga.equals(Uloga.KUPAC)){
            kupacRepository.save((Kupac) korisnik);
        }else if (uloga.equals(Uloga.DOSTAVLJAC)){
            dostavljacRepository.save((Dostavljac) korisnik);
        }
        return korisnikRepository.save(korisnik);
    }

    public Korisnik getByKorisnickoIme(String korisnickoIme) {
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        return korisnik;
    }

    //registracija
    /*public String registracija(KorisnikDto korisnik){
        Kupac novi_korisnik = new Kupac(korisnik);
        kupacRepository.save(novi_korisnik);
        return "Registered successfully!!";
    }*/

  /*  public Korisnik registracija(KorisnikDto k1){
        if(k1.getUloga().equals(Uloga.KUPAC))
            k1.setAktivan(true);
             else k1.setAktivan(false);

        Korisnik korisnik = new Korisnik(
                k1.getId(),
                k1.getKorisnickoIme(),
                k1.getPrezime(),
                k1.getLozinka(),
                k1.getIme(),
                k1.getPol(),
                //k1.getDatum(),
                "",
                k1.getUloga(),
                k1.isAktivan()
        );
        korisnikRepository.save(korisnik);
        return korisnik;
    }*/

//prijava
    public Korisnik login(String korisnickoIme, String lozinka) {
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
            return null;
        return  korisnik;
    }

    public ResponseEntity<List<Korisnik>> kombPretraga(HttpSession session, String str) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");

        List<Restoran> listaRestorana = findAllRestoran();
        List<Restoran> potrebniRestorani = new ArrayList<>();

        for (Restoran restoran : listaRestorana) {
            if(restoran.getNaziv().contains(str) || restoran.getLokacija().getAdresa().contains(str) || restoran.getTipRestorana().contains(str))
                potrebniRestorani.add(restoran);
        }

        return new ResponseEntity(potrebniRestorani, HttpStatus.OK);

    }

    private List<Restoran> findAllRestoran() {
        return restoranRepository.findAll();
    }

}

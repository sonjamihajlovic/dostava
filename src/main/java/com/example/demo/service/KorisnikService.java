package com.example.demo.service;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.TipKupca;
import com.example.demo.entity.Uloga;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private KupacRepository kupacRepository;

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


    /*public String registracija(Korisnik korisnik){
        Kupac novi_korisnik = new Kupac(korisnik);
        kupacRepository.save(novi_korisnik);
        return "Registered successfully!!";
    }*/
    public Korisnik registracija(KorisnikDto k1){
        if(k1.getUloga()== Uloga.KUPAC){
            k1.setAktivan(true);
        }
        //else if(k1.getUloga() == Uloga.TRENER){
          //  k1.setAktivan(false);
       // }
        Korisnik korisnik = new Korisnik(
                k1.getId(),
                k1.getKorisnickoIme(),
                k1.getPrezime(),
                k1.getLozinka(),
                k1.getIme(),
                k1.getPol(),
                k1.getDatum(),
                k1.getUloga(),
                k1.isAktivan()
        );
        korisnikRepository.save(korisnik);
        return korisnik;
    }


    public Korisnik login(String korisnickoIme, String lozinka) {
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
            return null;
        return  korisnik;
    }

}

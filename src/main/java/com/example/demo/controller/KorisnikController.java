package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegistracijaDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.TipKupca;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KupacService kupacService;

    //pocetna stranica
    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    //prijava
    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Korisnik ulogovaniKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("korisnik", ulogovaniKorisnik);
        return ResponseEntity.ok("Successfully logged in!");
    }

    //odjava
    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }

    //listanje svih korisnika
    @GetMapping(value = "api/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDto>> getAllKorisnik() {
        List<Korisnik> listaKorisnika = korisnikService.findAll();
        List<KorisnikDto> listaKorisnikDto = new ArrayList<>();

        for (Korisnik korisnik : listaKorisnika) {
            KorisnikDto korisnikDto = new KorisnikDto(
                    korisnik.getId(),
                    korisnik.getKorisnickoIme(),
                    korisnik.getPrezime(),
                    korisnik.getLozinka(),
                    korisnik.getIme(),
                    korisnik.getPol(),
                    new Date(),
                    korisnik.getUloga(),
                    korisnik.isAktivan()
            );

            listaKorisnikDto.add(korisnikDto);
        }


        return new ResponseEntity<>(listaKorisnikDto, HttpStatus.OK);
    }

    //registracija+postaje kupac
    @PostMapping("/api/register")
    public ResponseEntity registrujKorisnika(@RequestBody RegistracijaDto newDto) {

        Kupac noviKupac = new Kupac();
        noviKupac.setKorisnickoIme(newDto.getKorisnickoIme());
        noviKupac.setIme(newDto.getIme());
        noviKupac.setPrezime(newDto.getPrezime());
        noviKupac.setDatum(newDto.getDatum());
        noviKupac.setPol(newDto.getPol());
        noviKupac.setLozinka(newDto.getLozinka());
        noviKupac.setUloga(Uloga.KUPAC);
        noviKupac.setBrojBodova(0);

        try{
            this.kupacService.save(noviKupac);
            return new ResponseEntity("Korisnik uspesno registrovan!", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Korisnik neuspesno registrovan!", HttpStatus.NOT_FOUND);
        }

       // return "Korisnik " + newDto.getKorisnickoIme() + " je uspesno registrovan";
    }

    //editovanje korisnika
    @PutMapping("/api/korisnik/edit")
    public String editKorisnik(@RequestBody Korisnik korisnik) {
        Korisnik editKorisnik = korisnikService.getByKorisnickoIme(korisnik.getKorisnickoIme());
        editKorisnik.setIme(korisnik.getIme());
        editKorisnik.setPrezime(korisnik.getPrezime());
        editKorisnik.setDatum(korisnik.getDatum());
        editKorisnik.setLozinka(korisnik.getLozinka());
        editKorisnik.setPol(korisnik.getPol());
        this.korisnikService.save(editKorisnik);
        return "Uspesno sacuvan korisnik!";
    }

    //DODATAK-PROFIL JEDNOG KORISNIKA
    @GetMapping("/api/korisnici/{id}")
    public Korisnik getKorisnik(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        session.invalidate();
        return korisnikService.findOne(id);
    }




}

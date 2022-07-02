package com.example.demo.controller;

import com.example.demo.dto.ArtikalDto;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class MenadzerController {

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private ArtikalService artikalService;

    //menadzer trazenje restorana
    @GetMapping("/api/menadzer/restoran")
    public ResponseEntity<Restoran> getMyRestaurant(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani.getUloga() != Uloga.MENADZER || logovani == null) {
            return new ResponseEntity("Ne mozete da vidite restoran", HttpStatus.FORBIDDEN);
        }

       Restoran r=menadzerService.nadjiRestoran(logovani);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    //pregled porudzbina
    @GetMapping("/api/menadzer/porudzbine_restorana")
    public ResponseEntity<Set<Porudzbina>> getMyOrders(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.MENADZER) {
            return new ResponseEntity("Ne mozete da vidite porudzbine restorana", HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> set=menadzerService.nadjiPorudzbine(logovani);
        return new ResponseEntity<Set<Porudzbina>>(set, HttpStatus.OK);
    }

    //menadzer kreira artikal
    @PostMapping("/api/novi-artikal")
    public ResponseEntity napraviArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.MENADZER) {
            return new ResponseEntity("Ne mozete da napravite artikal", HttpStatus.FORBIDDEN);
        }

        Artikal artikal = new Artikal();
        artikal.setNaziv(artikalDto.getNaziv());
        artikal.setCena(artikalDto.getCena());
        artikal.setTip(artikalDto.getTip());
        artikal.setOpis(artikalDto.getOpis());
        artikal.setKolicina(artikalDto.getKolicina());

        this.menadzerService.saveArtikal(artikal);
        return new ResponseEntity("Dodali ste artikal", HttpStatus.CREATED);
    }

    //editovanje artikla
    @PutMapping("/api/izmena-artikla")
    public String editArtikal(@RequestBody Artikal artikal,  HttpSession session) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.MENADZER) {
            return "Nemate prava na izmenu artikla";
        }

        Artikal editArtikal = artikalService.nadjiArtikal(artikal.getId());
        editArtikal.setCena(artikal.getCena());
        editArtikal.setKolicina(artikal.getKolicina());
        editArtikal.setNaziv(artikal.getNaziv());
        editArtikal.setOpis(artikal.getOpis());
        this.artikalService.save(editArtikal);
        return "Uspesno ste izmenili artikal";
    }

    //brisanje artikla, mora se obrisati samo iz liste artikala u ponudi
    @DeleteMapping(value="/api/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteArtikal(@PathVariable Long id){
        try{
            artikalService.delete(id);
            return new ResponseEntity("Artikal uspesno obrisan!", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Artikal sa tim id-jem ne postoji!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/menadzer-menja-status")
    public ResponseEntity priprema(HttpSession session, String korisnickoIme){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");

        if (logovani == null || logovani.getUloga() != Uloga.MENADZER)
            return new ResponseEntity("Nemate prava na porucivanje, jer niste kupac.", HttpStatus.FORBIDDEN);

        Kupac kupac = (Kupac) korisnikService.getByKorisnickoIme(korisnickoIme);
        Menadzer menadzer = (Menadzer) session.getAttribute("korisnik");

        if(porudzbinaService.findByStatus(kupac,Status.OBRADA) != null){
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.OBRADA);
            porudzbina.setStatus(Status.U_PRIPREMI);
            porudzbinaService.save(porudzbina);
            korisnikService.save(kupac, kupac.getUloga());
            return new ResponseEntity("Porudzbina se priprema.", HttpStatus.OK);
        }

        if(porudzbinaService.findByStatus(kupac,Status.U_PRIPREMI) != null){
            Porudzbina porudzbina = porudzbinaService.findByStatus(kupac,Status.U_PRIPREMI);
            porudzbina.setStatus(Status.CEKA_DOSTAVLJACA);
            porudzbinaService.save(porudzbina);
            korisnikService.save(kupac, kupac.getUloga());
            return new ResponseEntity("Porudzbina ceka dostavljaca.", HttpStatus.OK);
        }
        return new ResponseEntity("Porudzbina nije spremna!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/menadzer/promeni-status/{id}")
    public ResponseEntity uPripremi(@PathVariable(name = "id") String id, HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");

        if (logovani == null || logovani.getUloga() != Uloga.MENADZER)
            return new ResponseEntity("Nemate prava da menjate status, jer niste menadzer.", HttpStatus.FORBIDDEN);


        Menadzer menadzer = (Menadzer) logovani;
        Porudzbina porudzbina = porudzbinaService.promeniStatusMenadzer(menadzer.getRestoran(), id);


        return new ResponseEntity("Izmenili ste status porudzbine.", HttpStatus.OK);
    }








}

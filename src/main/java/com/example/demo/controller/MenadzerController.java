package com.example.demo.controller;

import com.example.demo.dto.ArtikalDto;
import com.example.demo.entity.*;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.RestoranService;
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
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    @GetMapping("/api/menadzer/restoran")
    public ResponseEntity<Restoran> getMyRestaurant(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani.getUloga() != Uloga.MENADZER || logovani == null) {
            return new ResponseEntity("Ne mozete da vidite restoran", HttpStatus.FORBIDDEN);
        }

       Restoran r=menadzerService.nadjiRestoran(logovani);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    @GetMapping("/api/menadzer/porudzbine_restorana")
    public ResponseEntity<Set<Porudzbina>> getMyOrders(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.MENADZER) {
            return new ResponseEntity("Ne mozete da vidite porudzbine restorana", HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> set=menadzerService.nadjiPorudzbine(logovani);
        return new ResponseEntity<Set<Porudzbina>>(set, HttpStatus.OK);
    }

    //menadzer kreira artikal, treba ga dodati u restoran za koji je zaduzen taj menadzer
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
        //artikal.setSlika(artikalDto.getSlika());

        //Restoran restoran = restoranService.findOne(artikalDto.getResId());
        //artikal.setRestoran(restoran);

        this.menadzerService.saveArtikal(artikal);
        return new ResponseEntity("Dodali ste artikal", HttpStatus.CREATED);
    }

    //artikal edit
   /* @PutMapping("/api/izmena-artikla")
    public String editArtikal(@RequestBody Artikal artikal,  HttpSession session) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.MENADZER) {
            return "Nemate prava na izmenu artikla";
            //return new ResponseEntity("Ne mozete da promenite artikal", HttpStatus.FORBIDDEN);
        }

        Artikal editArtikal = ArtikalService.nadjiArtikal(artikal.getId());
        editArtikal.setCena(artikal.getCena());
        editArtikal.setKolicina(artikal.getKolicina());
        editArtikal.setNaziv(artikal.getNaziv());
        editArtikal.setOpis(artikal.getOpis());
        //editArtikal.setTip(artikal.getTip());
        this.artikalService.save(editArtikal);
        return "Uspesno ste izmenili artikal";
       // return new ResponseEntity("Uspesno ste izmenili artikal", HttpStatus.CREATED);
    }*/

    @DeleteMapping(value="/api/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteArtikal(@PathVariable Long id){
        try{
            artikalService.delete(id);
            return new ResponseEntity("Artikal uspesno obrisan!", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Artikal sa tim id-jem ne postoji!", HttpStatus.NOT_FOUND);
        }
    }

}

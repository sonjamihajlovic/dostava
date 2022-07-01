package com.example.demo.controller;

import com.example.demo.dto.KomentarDto;
import com.example.demo.entity.*;
import com.example.demo.service.KomentarService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class KomentarController {
    @Autowired
    KomentarService komentarService;

    @Autowired
    PorudzbinaService porudzbinaService;

    //od ane
   /* @PostMapping("/api/komentari/{uuid}")
    public ResponseEntity<String> addComment(@PathVariable(name = "uuid") String uuidPorudzbine, KomentarDto komentarDto, HttpSession session) {
        return komentarService.addComment(UUID.fromString(uuidPorudzbine), komentarDto, session);
    }*/

    //od luke
    /*@PostMapping("/api/komentari/{uuid}")
    public ResponseEntity<String> ostaviKomentar(@PathVariable(name = "uuid") String uuidPorudzbine, @RequestBody KomentarDto dto, HttpSession session) {

        UUID uuid_porudzbine = UUID.fromString(uuidPorudzbine);

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(uk.getUloga() != Uloga.KUPAC)
            return new ResponseEntity("Funkcionalnost je dostupna samo kupcima.", HttpStatus.BAD_REQUEST);

        Porudzbina porudzbina = new Porudzbina();
        porudzbina = this.porudzbinaService.findOneByUuid(uuid_porudzbine);

        if (porudzbina == null) {
            return new ResponseEntity("Porudzbina nije pronadjena.", HttpStatus.BAD_REQUEST);
        }
        if (porudzbina.getStatus() == Status.DOSTAVLJENA) {
            Kupac kupac = (Kupac)uk;
            Komentar noviKomentar = new Komentar(kupac, porudzbina.getRestoran(), dto.getText(), dto.getOcena());

            this.porudzbinaService.save(noviKomentar);
        }
        else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Uspesno dodat komentar!");
    }*/




}

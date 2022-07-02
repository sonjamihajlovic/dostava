package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class DostavljacController {

    @Autowired
    public DostavljacService dostavljacService;

    @Autowired
    public PorudzbinaService porudzbinaService;

    //pregled porudzbina
    @GetMapping("/api/dostavljac/porudzbine")
    public ResponseEntity<Set<Porudzbina>> dostavljacPorudzbine(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.DOSTAVLJAC) {
            return new ResponseEntity("Ne mozete da vidite porudzbine, jer niste dostavljac", HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> set=dostavljacService.izlistajPorudzbine1(logovani);
        return new ResponseEntity<Set<Porudzbina>>(set, HttpStatus.OK);
    }

    //pokusaj popravke dostavljac pregled porudzbina u statusu ceka dostavljaca
    @GetMapping("/api/dostavljac-porudzbine")
    public ResponseEntity<List<Porudzbina>> pregledPorudzbina(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.DOSTAVLJAC) {
            return new ResponseEntity("Ne mozete da vidite svoje porudzbine, jer niste dostavljac", HttpStatus.FORBIDDEN);
        }
        //Kupac logovanik = () session.getAttribute("korisnik");
        List<Porudzbina> lista = porudzbinaService.findAllByStatus(Status.CEKA_DOSTAVLJACA);
        return ResponseEntity.ok(lista);
    }










}

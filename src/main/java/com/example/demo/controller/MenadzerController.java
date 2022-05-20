package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class MenadzerController {

    @Autowired
    private MenadzerService menadzerService;

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






}

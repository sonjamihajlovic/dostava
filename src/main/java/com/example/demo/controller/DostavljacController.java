package com.example.demo.controller;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Uloga;
import com.example.demo.service.DostavljacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class DostavljacController {

    @Autowired
    public DostavljacService dostavljacService;

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

}

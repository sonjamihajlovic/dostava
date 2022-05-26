package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class KupacController {

    @Autowired
    public KupacService kupacService;

    //pregled porudzbina
    @GetMapping("/api/kupac/porudzbine")
    public ResponseEntity<Set<Porudzbina>> kupacPorudzbine(HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani == null || logovani.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity("Ne mozete da vidite svoje porudzbine", HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> set=kupacService.izlistajPorudzbine(logovani);
        return new ResponseEntity<Set<Porudzbina>>(set, HttpStatus.OK);
    }

}

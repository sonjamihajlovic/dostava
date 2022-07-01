package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class PorudzbinaController {

        @Autowired
        private PorudzbinaService porudzbinaService;

        //DOPUNA 2. KONTROLNE TACKE
        @GetMapping("/api/dostave")
        public ResponseEntity<Set<PorudzbinaDto>> getPorudzbineDelivery(HttpSession session){
                Korisnik logovaniKorisnik = (Dostavljac)session.getAttribute("korisnik");
                if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.DOSTAVLJAC) {
                        return new ResponseEntity("You are not permmitet to do that!", HttpStatus.FORBIDDEN);
                }
                Set<PorudzbinaDto> porudzbinas = porudzbinaService.PorudzbineDostavljaca(logovaniKorisnik);
                return ResponseEntity.ok(porudzbinas);
        }
}

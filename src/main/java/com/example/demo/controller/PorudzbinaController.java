package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class PorudzbinaController {

        @Autowired
        private PorudzbinaService porudzbinaService;

        @Autowired
        private RestoranService restoranService;

        @Autowired
        private ArtikalService artikalService;

        //DOPUNA 2. KONTROLNE TACKE
        @GetMapping("/api/dostave")
        public ResponseEntity<Set<PorudzbinaDto>> getPorudzbineDelivery(HttpSession session){
                Korisnik logovaniKorisnik = (Korisnik)session.getAttribute("korisnik");
                if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.DOSTAVLJAC) {
                        return new ResponseEntity("You are not permmitet to do that!", HttpStatus.FORBIDDEN);
                }
                Set<PorudzbinaDto> porudzbinas = porudzbinaService.PorudzbineDostavljaca(logovaniKorisnik);
                return ResponseEntity.ok(porudzbinas);
        }



        //KORPA
        //kreiranje porudzbine
        @PostMapping("/api/napravi-porudzbinu/{id}")
        public ResponseEntity<String> napraviPorudzbinu(@PathVariable(name = "id") long idRestorana, HttpSession session){
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if(logovani == null || logovani.getUloga() != Uloga.KUPAC)
                        return new ResponseEntity("Nemate prava na kreiranje porudzbine, jer niste kupac.", HttpStatus.FORBIDDEN);
                Kupac logovaniKupac = (Kupac) session.getAttribute("korisnik");

                Restoran potrebniRestoran = restoranService.findOneById(idRestorana);
                        if(potrebniRestoran == null)
                                return new ResponseEntity("Restoran ne postoji.", HttpStatus.NOT_FOUND);

                        if(potrebniRestoran.getStatusRestorana() == StatusRestorana.NE_RADI){
                                return new ResponseEntity("Restoran ne radi", HttpStatus.FORBIDDEN);
                        }else {
                                List<Porudzbina> listazakorpu = porudzbinaService.findAllByStatusAndKupacid(Status.OBRADA, logovani.getId());
                                for (Porudzbina por : listazakorpu) {
                                        if (por != null) {
                                                por.setRestoran(null);
                                                por.setStatus(Status.OTKAZANA);
                                                porudzbinaService.save(por);
                                        }
                                }

                                Porudzbina novaPor = new Porudzbina();
                                novaPor.setKupac(logovaniKupac);
                                novaPor.setStatus(Status.OBRADA);
                                novaPor.setRestoran(potrebniRestoran);
                                porudzbinaService.save(novaPor);
                                return ResponseEntity.ok("Kreirali ste porudzbinu!\n");
                        }

        }


















}

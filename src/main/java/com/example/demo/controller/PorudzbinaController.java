package com.example.demo.controller;

import com.example.demo.dto.ArtikalDto;
import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class PorudzbinaController {

        @Autowired
        private PorudzbinaService porudzbinaService;

        @Autowired
        private KorisnikService korisnikService;

        @Autowired
        private RestoranService restoranService;

        @Autowired
        private ArtikalService artikalService;

        @Autowired
        private StavkaPorudzbineService stavkaPorudzbineService;

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

        @PostMapping("/api/dodaj-u-korpu/{id}")
        public ResponseEntity dodajUKorpu(@PathVariable Long id, HttpSession session){
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if(logovani == null || logovani.getUloga() != Uloga.KUPAC)
                        return new ResponseEntity("Nemate prava na dodavanje artikla u korpu, jer niste kupac.", HttpStatus.FORBIDDEN);

                StavkaPorudzbine stavka = stavkaPorudzbineService.findOne(id);

                Restoran restoran = stavka.getRestoran();

                Kupac kupac = (Kupac) session.getAttribute("korisnik");

                Porudzbina porudzbina = porudzbinaService.findByStatus(kupac, Status.U_KORPI);

                porudzbina.setKorisnickoIme(kupac.getKorisnickoIme());
                porudzbina.setCena(stavka.getArtikal().getCena() + porudzbina.getCena());
                porudzbina.setVremePorudzbine(new Date(101, Calendar.AUGUST, 21));
                porudzbina.setStatus(Status.U_KORPI);
                porudzbina.getStavkePorudzbina().add(stavka);
                porudzbina.setRestoran(restoran);

                kupac.getSvePorudzbine().add(porudzbina);

                porudzbinaService.save(porudzbina);
                korisnikService.save(kupac,Uloga.KUPAC);
                return new ResponseEntity("Artikal je dodat", HttpStatus.OK);
        }

        @DeleteMapping("/api/izbrisi-iz-korpe/{id}")
        public ResponseEntity izbaciIzKorpe(@PathVariable Long id, HttpSession session){
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if(logovani == null || logovani.getUloga() != Uloga.KUPAC)
                        return new ResponseEntity("Nemate prava na brisanje artikla iz korpe, jer niste kupac.", HttpStatus.FORBIDDEN);

                Kupac kupac = (Kupac) session.getAttribute("korisnik");

                Porudzbina porudzbina = porudzbinaService.findByStatus(kupac, Status.U_KORPI);

                porudzbinaService.ukloniArtikal(porudzbina, kupac, id);

                return new ResponseEntity("Uspesno obrisan artikal", HttpStatus.OK);
        }

      /*  @PutMapping("/api/artikli/updateArtikal/{id}")
        public ResponseEntity updateArtikal(@PathVariable(name = "id") Long id, @RequestBody ArtikalDto artikalDto, HttpSession session){
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if(logovani == null || logovani.getUloga() != Uloga.KUPAC)
                        return new ResponseEntity("Nemate prava na brisanje artikla iz korpe, jer niste kupac.", HttpStatus.FORBIDDEN);

                Kupac kupac = (Kupac) session.getAttribute("korisnik");

                Menadzer menadzer = (Menadzer) session.getAttribute("user");

                artikalService.update(id, artikalDto, menadzer);

                return ResponseEntity.ok("Successfuly updated!");
        }



*/







}

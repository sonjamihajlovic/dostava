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
import java.util.*;

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
        public ResponseEntity<Set<PorudzbinaDto>> getPorudzbineDelivery(HttpSession session) {
                Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
                if (logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.DOSTAVLJAC) {
                        return new ResponseEntity("You are not permmitet to do that!", HttpStatus.FORBIDDEN);
                }
                Set<PorudzbinaDto> porudzbinas = porudzbinaService.PorudzbineDostavljaca(logovaniKorisnik);
                return ResponseEntity.ok(porudzbinas);
        }


        //KORPA
        //kreiranje porudzbine
        @PostMapping("/api/napravi-porudzbinu/{id}")
        public ResponseEntity<String> napraviPorudzbinu(@PathVariable(name = "id") long idRestorana, HttpSession session) {
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if (logovani == null || logovani.getUloga() != Uloga.KUPAC)
                        return new ResponseEntity("Nemate prava na kreiranje porudzbine, jer niste kupac.", HttpStatus.FORBIDDEN);
                Kupac logovaniKupac = (Kupac) session.getAttribute("korisnik");

                Restoran potrebniRestoran = restoranService.findOneById(idRestorana);
                if (potrebniRestoran == null)
                        return new ResponseEntity("Restoran ne postoji.", HttpStatus.NOT_FOUND);

                if (potrebniRestoran.getStatusRestorana() == StatusRestorana.NE_RADI) {
                        return new ResponseEntity("Restoran ne radi", HttpStatus.FORBIDDEN);
                } else {
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
        public ResponseEntity dodajUKorpu(@PathVariable Long id, HttpSession session) {
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if (logovani == null || logovani.getUloga() != Uloga.KUPAC)
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
                korisnikService.save(kupac, Uloga.KUPAC);
                return new ResponseEntity("Artikal je dodat", HttpStatus.OK);
        }

        @DeleteMapping("/api/izbrisi-iz-korpe/{id}")
        public ResponseEntity izbaciIzKorpe(@PathVariable Long id, HttpSession session) {
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
                if (logovani == null || logovani.getUloga() != Uloga.KUPAC)
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

        @PutMapping("/api/poruci")
        public ResponseEntity<String> porucivanje(HttpSession session) {
                Korisnik logovani = (Korisnik) session.getAttribute("korisnik");

                if (logovani == null || logovani.getUloga() != Uloga.KUPAC) {
                        return new ResponseEntity("Nemate prava na porucivanje, jer niste kupac.", HttpStatus.FORBIDDEN);
                } else {
                        Kupac kupac = (Kupac) session.getAttribute("korisnik");
                                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                                Porudzbina korpa = porudzbinaService.findFirstByStatus(Status.U_PRIPREMI, ulogovaniKupac.getId());
                                korpa.setStatus(Status.OBRADA);
                                korpa.setCena(korpa.racunajCenu());

                                porudzbinaService.save(korpa);
                        return ResponseEntity.ok("Uspesno porucivanje!");
                }

        }

        @PutMapping("/api/dostavljac-menja-status/{uuid}")
        public ResponseEntity<String> dostavljacMenjaStatus(@PathVariable(name = "uuid") String uuidPorudzbine, HttpSession session) {

                UUID uuid_porudzbine = UUID.fromString(uuidPorudzbine);

                Korisnik uk = (Korisnik) session.getAttribute("korisnik");

                if (uk == null) {
                        return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
                }

                if (uk.getUloga() != Uloga.DOSTAVLJAC) {
                        return new ResponseEntity("Nemate prava da menjate status.", HttpStatus.UNAUTHORIZED);
                }

                Porudzbina porudzbina = new Porudzbina();
                porudzbina = this.porudzbinaService.findOneByUuid(uuid_porudzbine);

                if (porudzbina == null) {
                        return new ResponseEntity("Porudzbina nije pronadjena.", HttpStatus.BAD_REQUEST);
                }

                if(porudzbina.getStatus()==Status.U_PRIPREMI) return new ResponseEntity("Jos uvek ne mozete da vidite porudzbinu, jer je i dalje u pripremi.", HttpStatus.BAD_REQUEST);

                if (porudzbina.getStatus() == Status.CEKA_DOSTAVLJACA || porudzbina.getStatus() == Status.U_TRANSPORTU) {

                        if (porudzbina.getStatus() == Status.CEKA_DOSTAVLJACA) {
                                porudzbina.setStatus(Status.U_TRANSPORTU);
                        }
                        else {
                                porudzbina.setStatus(Status.DOSTAVLJENA);

                                this.porudzbinaService.saveKupac(porudzbina);
                        }
                        this.porudzbinaService.save(porudzbina);
                }
                return ResponseEntity.ok("Promenili ste status porudzbine u: "+ porudzbina.getStatus().name()+".");
        }














}

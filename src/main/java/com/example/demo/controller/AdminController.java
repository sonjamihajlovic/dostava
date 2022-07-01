package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private LokacijaService lokacijaService;


    //pregled svih korisnika od strane admina
    @GetMapping("/api/korisnici-admin")
    public ResponseEntity<List<KorisnikDto>> korisnici(HttpSession session) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if (logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava na uvid korisnika!", HttpStatus.FORBIDDEN);
        }

        List<Korisnik> listaKorisnika = korisnikService.findAll();
        List<KorisnikDto> listaKorisnikDto = new ArrayList<>();

        for (Korisnik korisnik : listaKorisnika) {
            KorisnikDto korisnikDto = new KorisnikDto(
                    korisnik.getId(),
                    korisnik.getKorisnickoIme(),
                    korisnik.getPrezime(),
                    korisnik.getLozinka(),
                    korisnik.getIme(),
                    korisnik.getPol(),
                    new Date(),
                    korisnik.getUloga(),
                    korisnik.isAktivan()
            );

            listaKorisnikDto.add(korisnikDto);
        }

        return new ResponseEntity(listaKorisnikDto, HttpStatus.OK);

    }

    //admin dodaje menadzera
    @PostMapping("/api/add-menadzera")
    public ResponseEntity dodaj_menadzera(@RequestBody Korisnik korisnik, HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava da dodate menadzera", HttpStatus.FORBIDDEN);
        }
        String izlaz = menadzerService.dodajMenadzer(korisnik);
        return ResponseEntity.ok(izlaz);
    }

    //admin dodaje dostavljaca
    @PostMapping("/api/add-dostavljaca")
    public ResponseEntity dodaj_dostavljaca(@RequestBody Korisnik korisnik, HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava da dodate dostavljaca", HttpStatus.FORBIDDEN);
        }

        Dostavljac dostavljac = new Dostavljac(korisnik);
        dostavljacService.save(dostavljac);
        return new ResponseEntity("Dodali ste dostavljaca", HttpStatus.CREATED);
    }

    //admin dodaje restoran
    @PostMapping("/api/add-restoran")
    public ResponseEntity dodaj_restoran(@RequestBody RestoranDto restoranDto, HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava da dodate restoran", HttpStatus.FORBIDDEN);
        }

        Restoran r = new Restoran();
        r.setNaziv(restoranDto.getNaziv());
        r.setTipRestorana(restoranDto.getTipRestorana());
        Lokacija lokacija=new Lokacija(0, 0, restoranDto.getAdresa());
        Lokacija sacuvanaLokacija=lokacijaService.save(lokacija);
        r.setLokacija(sacuvanaLokacija);

        restoranService.save(r);

        return new ResponseEntity("Dodali ste restoran", HttpStatus.CREATED);
    }

    @GetMapping("/api/admin-pretraga/{str}")
    public ResponseEntity<List<Korisnik>> pretragaKorisnika(HttpSession session, @PathVariable String str){
        return adminService.adminPretraga(session, str);
    }


































    //admin bira menadzera nadleznog za restoran
   /* @PostMapping("/api/menadzer/add-restoran")
    public ResponseEntity<String> dodeliRestoranMenadzeru(@RequestBody RestoranMenadzerDto restoranMenadzerDto) {

        Restoran restoran = restoranService.findOne(restoranMenadzerDto.getRestoranId());
        Menadzer menadzer = (Menadzer) korisnikService.findOne(restoranMenadzerDto.getMenadzerId());
        menadzer.setRestoran(restoran);
        menadzerService.saveMenadzer(menadzer);

        return ResponseEntity.ok("Uspesno dodeljen restoran menadzeru!");
    }

*/
}

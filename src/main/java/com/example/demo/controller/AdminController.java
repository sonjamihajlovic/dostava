package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Uloga;
import com.example.demo.service.AdminService;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

        //ako se skonta kako da se doda naslov
        //new ResponseEntity("Dobili ste spisak korisnika", HttpStatus.OK);
        return new ResponseEntity(listaKorisnikDto, HttpStatus.OK);

    }

    @PostMapping("/api/add-menadzera")
    public ResponseEntity dodaj_menadzera(@RequestBody Korisnik korisnik, HttpSession session){
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if(logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava da dodate menadzera", HttpStatus.FORBIDDEN);
        }
        String izlaz = menadzerService.dodajMenadzer(korisnik);
        return ResponseEntity.ok(izlaz);
    }


















}

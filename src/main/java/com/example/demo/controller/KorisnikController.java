package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegistracijaDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Korisnik ulogovaniKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("korisnik", ulogovaniKorisnik);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }
    @GetMapping(value = "api/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDto>> getAllKorisnik() {
        List<Korisnik> listaKorisnika = korisnikService.findAll();
        List<KorisnikDto> listaKorisnikDto = new ArrayList<>();

        for (Korisnik korisnik : listaKorisnika) {
            KorisnikDto korisnikDto = new KorisnikDto(
                    korisnik.getId(),
                    korisnik.getKorisnickoIme(),
                    korisnik.getPrezime(),
                    korisnik.getLozinka(),
                    korisnik.getIme(),

                    korisnik.getDatum(),
                    korisnik.getUloga(),
                    korisnik.isAktivan()
            );

            listaKorisnikDto.add(korisnikDto);
        }


        return new ResponseEntity<>(listaKorisnikDto, HttpStatus.OK);
    }

   /* @PostMapping(value = "api/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDto> registracija(@RequestBody KorisnikDto k1) {
        Korisnik korisnik = korisnikService.registracija(k1);
        k1.setId(korisnik.getId());
        return new ResponseEntity<>(k1, HttpStatus.CREATED);
    }*/


}

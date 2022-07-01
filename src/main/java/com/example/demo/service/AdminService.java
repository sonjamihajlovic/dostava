package com.example.demo.service;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class AdminService {

    @Autowired
    public KorisnikRepository korisnikRepository;

    public ResponseEntity<List<Korisnik>> adminPretraga(HttpSession session, String str) {
        Korisnik logovani = (Korisnik) session.getAttribute("korisnik");
        if (logovani.getUloga() != Uloga.ADMIN || logovani == null) {
            return new ResponseEntity("Nemate prava na uvid korisnika!", HttpStatus.FORBIDDEN);
        }

        List<Korisnik> listaKorisnika = findAll();
        List<Korisnik> potrebniKorisnici = new ArrayList<>();

        for (Korisnik korisnik : listaKorisnika) {
            if(korisnik.getIme().contains(str) || korisnik.getPrezime().contains(str) || korisnik.getKorisnickoIme().contains(str))
                potrebniKorisnici.add(korisnik);
        }

        return new ResponseEntity(potrebniKorisnici, HttpStatus.OK);

    }

    private List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }


}

package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.Komentar;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Restoran;
import com.example.demo.service.KomentarService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class RestoranController {

    @Autowired
    public RestoranService restoranService;

    @Autowired
    public KomentarService komentarService;

    //izlistavanje svih restorana, vidljivi svima
    @GetMapping(value = "/api/restorani", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restoran>> getAllRestoran() {
       List<Restoran> restorani = restoranService.getAll();
        return ResponseEntity.ok(restorani);
    }

    //izlistavanje po nazivu
    @GetMapping("/api/restorani-naziv/{naziv}")
    public ResponseEntity<Restoran> getByNaziv(@PathVariable String naziv) {
        Restoran restoran = restoranService.getByNaziv(naziv);
        return ResponseEntity.ok(restoran);
    }

    @GetMapping("/api/restorani-lokacija/{lokacijaId}")
    public ResponseEntity<Restoran> getByNaziv(@PathVariable("lokacijaId") Long lokacijaId) {
        Restoran restoran = restoranService.getByLokacija(lokacijaId);
        return ResponseEntity.ok(restoran);
    }

    @GetMapping("/api/restorani-tip/{tip}")
    public ResponseEntity<List<Restoran>> getByTip(@PathVariable("tip") String tip) {
        List<Restoran> restorani = restoranService.getByTip(tip);
        return ResponseEntity.ok(restorani);
    }

    //izborom po id-ju korisnik dobija informacije o restoranu
    @GetMapping("/api/izbor-restorana/{id}")
    public ResponseEntity<RestoranDto> getById(@PathVariable("id") Long id) {

        Restoran restoran = restoranService.findOne(id);
        List<Komentar> komentari = komentarService.getAllByRestoranId(id);

        RestoranDto restoranDto = new RestoranDto();
        restoranDto.setNaziv(restoran.getNaziv());
        restoranDto.setTipRestorana(restoran.getTipRestorana());
        if(restoranDto.getStatus() != null) {
            restoranDto.setStatus(restoran.getStatusRestorana());
        }
        restoranDto.setIdLokacija(restoran.getLokacija().getId());
        restoranDto.setArtikli(restoran.getArtikli());
        restoranDto.setKomentari(komentari);

        return ResponseEntity.ok(restoranDto);
    }















}

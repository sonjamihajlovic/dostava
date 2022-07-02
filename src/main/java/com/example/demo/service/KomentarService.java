package com.example.demo.service;

import com.example.demo.dto.KomentarDto;
import com.example.demo.entity.*;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    public PorudzbinaService porudzbinaService;

    public Komentar save(Komentar komentar) {
        return komentarRepository.save(komentar);
    }

    public List<Komentar> getAllByRestoranId(Long RestoranId) {
        return komentarRepository.getByRestoranId(RestoranId);
    }

   /* public ResponseEntity<String> addComment(, KomentarDto komentarDto, HttpSession session) {
        Korisnik logovani = (Korisnik) session.getAttribute("Korisnik");
        if (logovani.getUloga() != Uloga.KUPAC || logovani == null)
            return new ResponseEntity("Nemate prava da dodate komentar", HttpStatus.FORBIDDEN);
            if (porudzbinaService.findByUuid(uuid) == null)
                return new ResponseEntity("Data porudzbina ne postoji.", HttpStatus.BAD_REQUEST);
            if (porudzbinaService.findByUuid(uuid).getStatus() != Status.DOSTAVLJENA)
                return new ResponseEntity("Ova porudzbina nije dostavljena!", HttpStatus.BAD_REQUEST);

            Porudzbina porudzbina = porudzbinaService.findByUuid(uuid);
            Kupac logovaniKupac = (Kupac) logovani;
            Komentar komentar = new Komentar(logovaniKupac, porudzbina.getRestoran(), komentarDto.getText(), komentarDto.getOcena());
            komentarRepository.save(komentar);
            return ResponseEntity.ok("Vas komentar je dodat");

        }*/
    public List<Komentar> findAll(){
        return komentarRepository.findAll();
    }




    }

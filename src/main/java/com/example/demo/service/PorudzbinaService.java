package com.example.demo.service;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KomentarService komentarService;


    public Set<PorudzbinaDto> PorudzbineDostavljaca (Korisnik korisnik){
        List<Porudzbina> porudzbinas= porudzbinaRepository.findAll();
        Set<PorudzbinaDto> porudzbinaDtos = new HashSet<>();
        for(Porudzbina p : porudzbinas){
            if(p.getStatus() == Status.CEKA_DOSTAVLJACA){
                PorudzbinaDto temp = new PorudzbinaDto(p);
                porudzbinaDtos.add(temp);
            }
        }
        return porudzbinaDtos;
    }

   // public Porudzbina findByUuid(UUID id) { return porudzbinaRepository.findById(id).get(); }

    public List<Porudzbina> findAllByStatus(Status status){return porudzbinaRepository.findAllByStatus(status);}


    public void save(Porudzbina porudzbina){
        porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina findOneByUuid(UUID uuid){
        Optional<Porudzbina> porudzbina = porudzbinaRepository.findByUuid(uuid);

        if(porudzbina.isPresent()){
            return porudzbina.get();
        }
        return null;
    }


    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> findAllByKupac(Kupac kupac){
        return porudzbinaRepository.findAllByKupac(kupac);
    }

    public List<Porudzbina> findAllByStatusAndKupacid(Status status, long id){
        return porudzbinaRepository.findAllByStatusAndKupac_Id(status, id);
    }

    public Komentar save(Komentar komentar){
        return this.komentarService.save(komentar);
    }

}

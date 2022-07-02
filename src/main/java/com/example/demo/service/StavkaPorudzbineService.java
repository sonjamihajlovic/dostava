package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.StavkaPorudzbine;
import com.example.demo.repository.StavkaPorudzbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StavkaPorudzbineService {
    @Autowired
    private StavkaPorudzbineRepository stavkaPorudzbineRepository;

    public StavkaPorudzbine getById(long id){
        return stavkaPorudzbineRepository.getById(id);
    }
    public StavkaPorudzbine save(StavkaPorudzbine stavka){return  stavkaPorudzbineRepository.save(stavka);}
    public void deleteById(long id){
        stavkaPorudzbineRepository.deleteById(id);
    }

    public StavkaPorudzbine findOne(Long id){
        Optional<StavkaPorudzbine> pronadjenaStavka = stavkaPorudzbineRepository.findById(id);
        if (pronadjenaStavka.isPresent())
            return pronadjenaStavka.get();
        return null;
    }
}
package com.example.demo.service;

import com.example.demo.entity.TipKupca;
import com.example.demo.repository.TipKupcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipKupcaService {
    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    public TipKupca findByName(String naziv) {
        return tipKupcaRepository.findByIme(naziv);

    }
}

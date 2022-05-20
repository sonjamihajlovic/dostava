package com.example.demo.service;

import com.example.demo.entity.Dostavljac;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DostavljacService {

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public Dostavljac save(Dostavljac dostavljac) {
        return dostavljacRepository.save(dostavljac);
    }
}

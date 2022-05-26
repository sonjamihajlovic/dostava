package com.example.demo.service;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Komentar;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komentarRepository;

    public Komentar save(Komentar komentar) {
        return komentarRepository.save(komentar);
    }

    public List<Komentar> getAllByRestoranId(Long RestoranId) {
        return komentarRepository.getByRestoranId(RestoranId);
    }
}

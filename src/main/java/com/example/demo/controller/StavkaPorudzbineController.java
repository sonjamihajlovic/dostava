package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.StavkaPorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class StavkaPorudzbineController {

        @Autowired
        private PorudzbinaService porudzbinaService;

        @Autowired
        private StavkaPorudzbineService stavkaPorudzbineService;

        @Autowired
        private ArtikalService artikalService;




}

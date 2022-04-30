package com.example.demo.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Admin extends Korisnik implements Serializable {
    public Admin(Long id, String korisnickoIme, String prezime, String lozinka, String ime, String pol, String datum, Uloga uloga) {
        super(id, korisnickoIme, prezime, lozinka, ime, pol, datum, uloga);
    }
    // nasledjuje korisnika, nema specifikaciju
    public Admin() {
    }
}

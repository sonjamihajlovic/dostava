package com.example.demo.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Admin extends Korisnik implements Serializable {
    public Admin(Long id, String korisnickoIme, String prezime, String lozinka, String ime, Pol pol, Date datum, Uloga uloga) {
        super(id, korisnickoIme, prezime, lozinka, ime, pol, datum, uloga);
    }
    // nasledjuje korisnika, nema specifikaciju
    public Admin() {
    }
}

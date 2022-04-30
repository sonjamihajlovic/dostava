package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String korisnickoIme;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    private String lozinka;

    @Column(nullable = false)
    private String ime;

    //pol je enumeracija
    @Column
    private String pol;

    //date klasa
    @Column
    private String datum;

    @Column
    @Enumerated(EnumType.STRING)
    private Uloga uloga;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getKorisnickoIme() {return korisnickoIme;}

    public void setKorisnickoIme(String kIme) {this.korisnickoIme = kIme;}

    public String getPrezime() {return prezime;}

    public void setPrezime(String prezime) {this.prezime = prezime;}

    public String getLozinka() {return lozinka;}

    public void setLozinka(String lozinka) {this.lozinka = lozinka;}

    public String getIme() {return ime;}

    public void setIme(String ime) {this.ime = ime;}

    public String getPol() {return pol;}

    public void setPol(String pol) {this.pol = pol;}

    public String getDatum() {return datum;}

    public void setDatum(String datum) {this.datum = datum;}

    public Uloga getUloga() {return uloga;}

    public void setUloga(Uloga uloga) {this.uloga = uloga;}

    public Korisnik(Long id, String korisnickoIme, String prezime, String lozinka, String ime, String pol, String datum, Uloga uloga) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.pol = pol;
        this.datum = datum;
        this.uloga = uloga;
    }

    public Korisnik() {
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", prezime='" + prezime + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", pol='" + pol + '\'' +
                ", datum='" + datum + '\'' +
                ", uloga=" + uloga +
                '}';
    }
}

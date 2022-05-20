package com.example.demo.dto;

import com.example.demo.entity.Pol;
import com.example.demo.entity.Uloga;

import java.util.Date;

public class KorisnikDto {

    private Long id;
    private String korisnickoIme;
    private String prezime;
    private String lozinka;
    private String ime;
    private Pol pol;
    private Date datum;
    private Uloga uloga;
    boolean aktivan;

    public KorisnikDto(Long id, String korisnickoIme, String prezime, String lozinka, String ime, Pol pol, Date datum, Uloga uloga, boolean aktivan) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.pol = pol;
        this.datum = datum;
        this.uloga = uloga;
        this.aktivan = aktivan;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Long getId() {
        return id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getIme() {
        return ime;
    }

    public Date getDatum() {
        return datum;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Pol getPol() {return pol;}

    public void setPol(Pol pol) {this.pol = pol;}

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public boolean getAktivan(boolean aktivan){
        return aktivan;
    }

    public KorisnikDto(Long id, String korisnickoIme, String prezime, String lozinka, String ime, Date datum, Pol pol, Uloga uloga, boolean aktivan) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.pol = pol;
        this.datum = datum;
        this.uloga = uloga;
        this.aktivan = aktivan;

    }

    public KorisnikDto() {
    }

}

package com.example.demo.dto;

import com.example.demo.entity.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PorudzbinaDto {
    private UUID uuid;
    private Long idKupca;
    private Long idRestoran;
    public Date vremePorudzbine;
    public double cena;
    private Status status;
    private Set<StavkaPorudzbine> stavke = new HashSet<>();
    private RestoranDto restoran;

    private KorisnikDto kupac;


    public Set<StavkaPorudzbine> getStavke() {
        return stavke;
    }

    public void setStavke(Set<StavkaPorudzbine> stavke) {
        this.stavke = stavke;
    }

    public UUID getUuid() {return uuid;}

    public void setUuid(UUID id) {this.uuid = id;}

    public Long getIdRestoran() {return idRestoran;}

    public void setIdRestoran(Long restoran) {this.idRestoran = restoran;}

    public Date getVremePorudzbine() {return vremePorudzbine;}

    public void setVremePorudzbine(Date vremePorudzbine) {this.vremePorudzbine = vremePorudzbine;}

    public double getCena() {return cena;}

    public void setCena(double cena) {this.cena = cena;}

    public Long getIdKupca() {return idKupca;}

    public void setIdKupca(Long kupac) {this.idKupca = kupac;}

    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status;}

    public PorudzbinaDto(double cena, Status status, Long idRestoran, Long idKupca, Date vremePorudzbine) {
        this.idRestoran = idRestoran;
        this.idKupca = idKupca;
        this.vremePorudzbine = vremePorudzbine;
        this.cena = cena;
        this.status = status;
    }
    public PorudzbinaDto() {
    }
    public PorudzbinaDto(Porudzbina p) {
        this.stavke = p.getStavke();
        this.restoran = new RestoranDto(p.getRestoran());
        this.vremePorudzbine = p.getVremePorudzbine();
        this.cena = p.getCena();
        this.kupac = new KorisnikDto(p.getKupac());
        this.status = p.getStatus();
        this.uuid = p.getUuid();
    }
}

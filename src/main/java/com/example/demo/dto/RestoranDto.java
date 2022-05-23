package com.example.demo.dto;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Komentar;
import com.example.demo.entity.Lokacija;
import com.example.demo.entity.StatusRestorana;

import java.util.List;
import java.util.Set;

public class RestoranDto {
    private Long id;
    private String naziv;
    private String tipRestorana ;
    public Long idLokacija;
    public String adresa;
    public StatusRestorana status;
    public List<Komentar> komentari;
    public Set<Artikal> artikli;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNaziv() {return naziv;}

    public void setNaziv(String naziv) {this.naziv = naziv;}

    public String getTipRestorana() {return tipRestorana;}

    public void setTipRestorana(String tipRestorana) {this.tipRestorana = tipRestorana;}

    public Long getIdLokacija() {return idLokacija;}

    public void setIdLokacija(Long idLokacija) {this.idLokacija = idLokacija;}

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public StatusRestorana getStatus() {
        return status;
    }

    public void setStatus(StatusRestorana status) {
        this.status = status;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public RestoranDto(Long id, String naziv, String tipRestorana, Long idLokacija, StatusRestorana status, String adresa, List<Komentar> komentari, Set<Artikal> artikli) {
        this.id = id;
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.idLokacija = idLokacija;
        this.status=status;
        this.adresa=adresa;
        this.komentari=komentari;
        this.artikli=artikli;
    }

    public RestoranDto() {



    }

    public String getAdresa() {
        return adresa;
    }
}

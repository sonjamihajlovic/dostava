package com.example.demo.dto;

import com.example.demo.entity.TipArtikla;

public class ArtikalDto {
    private Long id;
    private String naziv;
    private double cena;
    private TipArtikla tip;
    private double kolicina;
    private String opis;
    private Long resId;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNaziv() {return naziv;}

    public void setNaziv(String naziv) {this.naziv = naziv;}

    public double getCena() {return cena;}

    public void setCena(double cena) {this.cena = cena;}

    public TipArtikla getTip() {return tip;}

    public void setTip(TipArtikla tip) {this.tip = tip;}

    public double getKolicina() {return kolicina;}

    public void setKolicina(double kolicina) {this.kolicina = kolicina;}

    public String getOpis() {return opis;}

    public void setOpis(String opis) {this.opis = opis;}

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public ArtikalDto(Long id, String naziv, double cena, TipArtikla tip, double kolicina, String opis, Long resId) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.resId=resId;
    }

    public ArtikalDto() {
    }
}

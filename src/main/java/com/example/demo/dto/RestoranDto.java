package com.example.demo.dto;

import com.example.demo.entity.Lokacija;

public class RestoranDto {
    private Long id;
    private String naziv;
    private String tipRestorana ;
    public Long idLokacija;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNaziv() {return naziv;}

    public void setNaziv(String naziv) {this.naziv = naziv;}

    public String getTipRestorana() {return tipRestorana;}

    public void setTipRestorana(String tipRestorana) {this.tipRestorana = tipRestorana;}

    public Long getIdLokacija() {return idLokacija;}

    public void setIdLokacija(Long idLokacija) {this.idLokacija = idLokacija;}

    public RestoranDto(Long id, String naziv, String tipRestorana, Long idLokacija) {
        this.id = id;
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.idLokacija = idLokacija;
    }

    public RestoranDto() {
    }
}

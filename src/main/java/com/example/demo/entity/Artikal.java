package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String naziv;

    @Column(nullable = false)
    public double cena;

    //enum jelo pice
    @Column
    public String tip;

    @Column(nullable = false)
    public double kolicina;

    @Column
    public String opis;

    @ManyToMany(mappedBy = "poruceniArtikal")
    private Set<Porudzbina> svePorudzbine = new HashSet<>();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNaziv() {return naziv;}

    public void setNaziv(String naziv) {this.naziv = naziv;}

    public double getCena() {return cena;}

    public void setCena(double cena) {this.cena = cena;}

    public String getTip() {return tip;}

    public void setTip(String tip) {this.tip = tip;}

    public double getKolicina() {return kolicina;}

    public void setKolicina(double kolicina) {this.kolicina = kolicina;}

    public String getOpis() {return opis;}

    public void setOpis(String opis) {this.opis = opis;}

    public Set<Porudzbina> getSvePorudzbine() {return svePorudzbine;}

    public void setSvePorudzbine(Set<Porudzbina> svePorudzbine) {this.svePorudzbine = svePorudzbine;}

    public Artikal(Long id, String naziv, double cena, String tip, double kolicina, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Artikal() {
    }
}

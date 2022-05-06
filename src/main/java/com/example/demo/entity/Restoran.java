package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    //tipRestorana
    @Column
    private String tipRestorana ;

    @OneToMany( cascade =CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "artikal_id")
    private Set<Artikal> artikli = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "lokacija_id")
    public Lokacija lokacija;

    public Lokacija getLokacija() {return lokacija;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNaziv() {return naziv;}

    public void setNaziv(String naziv) {naziv = naziv;}

    public String getTipRestorana() {return tipRestorana;}

    public void setTipRestorana(String tipr) {this.tipRestorana = tipr;}

    public Set<Artikal> getArtikli() {return artikli;}

    public void setArtikli(Set<Artikal> artikli) {this.artikli = artikli;}

    public void setLokacija(Lokacija lokacija) {this.lokacija = lokacija;}

    public Restoran(Long id, String naziv, String tipr, Set<Artikal> artikli, Lokacija lokacija) {
        this.id = id;
        this.naziv = naziv;
        this.tipRestorana = tipr;
        this.artikli = artikli;
        this.lokacija = lokacija;
    }

    public Restoran() {
    }
}

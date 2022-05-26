package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //double URADJENO
    @Column
    public double geografskaSirina;

    @Column
    public double geografskaDuzina;

    @Column(nullable = false)
    public String adresa;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public double getGeografskaSirina() {return geografskaSirina;}

    public void setGeografskaSirina(double geografskaSirina) {this.geografskaSirina = geografskaSirina;}

    public double getGeografskaDuzina() {return geografskaDuzina;}

    public void setGeografskaDuzina(double geografskaDuzina) {this.geografskaDuzina = geografskaDuzina;}

    public String getAdresa() {return adresa;}

    public void setAdresa(String adresa) {this.adresa = adresa;}

    public Lokacija(double geografskaSirina, double geografskaDuzina, String adresa) {
        this.geografskaSirina = geografskaSirina;
        this.geografskaDuzina = geografskaDuzina;
        this.adresa = adresa;
    }

    public Lokacija() {
    }
}


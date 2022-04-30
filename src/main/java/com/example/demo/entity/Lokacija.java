package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //double
    @Column
    public String geografskaSirina;

    @Column
    public String geografskaDuzina;

    @Column(nullable = false)
    public String adresa;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getGeografskaSirina() {return geografskaSirina;}

    public void setGeografskaSirina(String geografskaSirina) {this.geografskaSirina = geografskaSirina;}

    public String getGeografskaDuzina() {return geografskaDuzina;}

    public void setGeografskaDuzina(String geografskaDuzina) {this.geografskaDuzina = geografskaDuzina;}

    public String getAdresa() {return adresa;}

    public void setAdresa(String adresa) {this.adresa = adresa;}

    public Lokacija(Long id, String geografskaSirina, String geografskaDuzina, String adresa) {
        this.id = id;
        this.geografskaSirina = geografskaSirina;
        this.geografskaDuzina = geografskaDuzina;
        this.adresa = adresa;
    }

    public Lokacija() {
    }
}


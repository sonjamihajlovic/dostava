package com.example.demo.dto;

import javax.persistence.Column;

public class LokacijaDto {

    private Long id;
    public double geografskaSirina;
    public double geografskaDuzina;

    public String adresa;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public double getGeografskaSirina() {return geografskaSirina;}

    public void setGeografskaSirina(double geografskaSirina) {this.geografskaSirina = geografskaSirina;}

    public double getGeografskaDuzina() {return geografskaDuzina;}

    public void setGeografskaDuzina(double geografskaDuzina) {this.geografskaDuzina = geografskaDuzina;}

    public String getAdresa() {return adresa;}

    public void setAdresa(String adresa) {this.adresa = adresa;}

    public LokacijaDto(Long id, double geografskaSirina, double geografskaDuzina, String adresa) {
        this.id = id;
        this.geografskaSirina = geografskaSirina;
        this.geografskaDuzina = geografskaDuzina;
        this.adresa = adresa;
    }

    public LokacijaDto() {
    }
}

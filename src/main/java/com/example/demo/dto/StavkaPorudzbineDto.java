package com.example.demo.dto;

public class StavkaPorudzbineDto {
    private Long id;
    public int kolicina;
    private Long idArtikla;



    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public int getKolicina() {return kolicina;}

    public void setKolicina(int kolicina) {this.kolicina = kolicina;}

    public Long getIdArtikla() {return idArtikla;}

    public void setIdArtikla(Long idArtikla) {this.idArtikla = idArtikla;}

    public StavkaPorudzbineDto(Long id, int kolicina, Long idArtikla) {
        this.id = id;
        this.kolicina = kolicina;
        this.idArtikla = idArtikla;
    }

    public StavkaPorudzbineDto() {
    }
}

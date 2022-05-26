package com.example.demo.dto;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Restoran;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class KomentarDto {

    private Long id;
    private String text;
    private int ocena;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public int getOcena() {return ocena;}

    public void setOcena(int ocena) {this.ocena = ocena;}


    public KomentarDto(Long id, String text, int ocena) {
        this.id = id;
        this.text = text;
        this.ocena = ocena;
    }

    public KomentarDto() {
    }
}

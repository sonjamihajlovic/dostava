package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Komentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @Column
    private int ocena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Kupac kupac;

    @ManyToOne(fetch = FetchType.EAGER)
    private Restoran restoran;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public int getOcena() {return ocena;}

    public void setOcena(int ocena) {this.ocena = ocena;}

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kup) {
        this.kupac = kup;
    }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran res) { this.restoran = res; }

    public Komentar(Long id, String text, int ocena) {
        this.id = id;
        this.text = text;
        this.ocena = ocena;
    }

    public Komentar() {
    }
}
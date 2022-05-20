package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private double procenat;

    @Column
    private static int brojBodova;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getIme() {return ime;}

    public void setIme(String ime) {this.ime = ime;}

    public double getProcenat() {return procenat;}

    public void setProcenat(double procenat) {this.procenat = procenat;}

    public int getBrojBodova() {return brojBodova;}

    public void setBrojBodova(int brojBodova) {this.brojBodova = brojBodova;}

    public TipKupca(Long id, String ime, double procenat) {
        this.id = id;
        this.ime = ime;
        this.procenat = procenat;
    }

    public TipKupca() {

    }
}


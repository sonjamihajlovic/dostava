package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {
    @Column
    public static int brojBodova;

    //many to one
    @OneToOne
    @JoinColumn(name = "tipkupca_id")
    public  TipKupca tipkupca;

    public TipKupca getTipkupca() {
        return tipkupca;
    }

    public static int getBrojBodova() {return brojBodova;}

    public static void setBrojBodova(int brojBodova) {Kupac.brojBodova = brojBodova;}

    public void setTipkupca(TipKupca tipkupca) {this.tipkupca = tipkupca;}

    @OneToMany( cascade =CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "porudzbina_id")
    public Set<Porudzbina> svePorudzbine;

    public Set<Porudzbina> getSvePorudzbine() {return svePorudzbine;}

    public void setSvePorudzbine(Set<Porudzbina> svePorudzbine) {this.svePorudzbine = svePorudzbine;}

    public Kupac(TipKupca  tipkupca, Set<Porudzbina> svePorudzbine) {
        this.svePorudzbine = svePorudzbine;
        this.tipkupca = tipkupca;
    }

    public Kupac() {
    }


}

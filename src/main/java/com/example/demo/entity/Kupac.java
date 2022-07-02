package com.example.demo.entity;

import com.example.demo.dto.KorisnikDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Kupac extends Korisnik implements Serializable {
    @Column
    public static int brojBodova;

    //many to one URADJENO
    @ManyToOne(fetch = FetchType.EAGER)
    public  TipKupca tipkupca;

    public Kupac(KorisnikDto korisnik) {
    }

    public TipKupca getTipkupca() {
        return tipkupca;
    }

    public int getBrojBodova() {return brojBodova;}

    public void setBrojBodova(int brojBodova) {this.brojBodova = brojBodova;}

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

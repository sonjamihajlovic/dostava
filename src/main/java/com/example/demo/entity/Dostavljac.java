package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "porudzbina_id")
    private Set<Porudzbina > porudzbine = new HashSet<>();

    public Set<Porudzbina> getPorudzbine() {return porudzbine;}

    public void setPorudzbine(Set<Porudzbina> porudzbine) {this.porudzbine = porudzbine;}

    public Dostavljac(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Dostavljac() {
    }
}

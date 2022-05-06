package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    //jedan menadzer je zaduzen za jedan restoran
    @OneToOne
    @JoinColumn(name = "restoran_id")
    public Restoran restoran;

    public void setRestoran(Restoran restoran) {this.restoran = restoran;}

    public Restoran getRestoran() {return restoran;}

    public Menadzer(Restoran restoran) {this.restoran = restoran;}

    public Menadzer() {
    }

}

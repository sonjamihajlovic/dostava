package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "UUID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Getter
    @Setter
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    private Kupac kupac;

    //posebna klasa, ima artikal i kolicinu koliko smo porucile
  //ne treba many to many
 //treba one to many da ima stavku porudzbine, a u stavci many to one
    @ManyToMany
    @JoinTable(name = "kupovina",
        joinColumns = @JoinColumn(name = "porudzbina_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "artikal_id", referencedColumnName = "id")
    )
    private Set<Artikal> poruceniArtikal = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Restoran restoran;

    @Column
    public Date vremePorudzbine;

    @Column(nullable = false)
    public double cena;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public UUID getUuid() {return uuid;}

    public void setUuid(UUID id) {this.uuid = id;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Artikal> getPoruceniArtikal() {return poruceniArtikal;}

    public void setPoruceniArtikal(Set<Artikal> poruceniArtikal) {this.poruceniArtikal = poruceniArtikal;}

    public Restoran getRestoran() {return restoran;}

    public void setRestoran(Restoran restoran) {this.restoran = restoran;}

    public Date getVremePorudzbine() {return vremePorudzbine;}

    public void setVremePorudzbine(Date vremePorudzbine) {this.vremePorudzbine = vremePorudzbine;}

    public double getCena() {return cena;}

    public void setCena(double cena) {this.cena = cena;}

    public Kupac getKupac() {return kupac;}

    public void setKupac(Kupac kupac) {this.kupac = kupac;}

    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status;}

    public Porudzbina(Long id, UUID uuid, Set<Artikal> poruceniArtikal, Restoran restoran, Date vremePorudzbine, double cena, Status status) {
        this.id=id;
        this.uuid = uuid;
        this.poruceniArtikal = poruceniArtikal;
        this.restoran = restoran;
        this.vremePorudzbine = vremePorudzbine;
        this.cena = cena;
        this.status = status;
    }
    public Porudzbina() {
    }
}


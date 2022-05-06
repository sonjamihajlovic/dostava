package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StavkaPorudzbine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public int kolicina;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artikal artikal;
}

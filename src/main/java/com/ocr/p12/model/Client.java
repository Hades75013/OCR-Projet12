package com.ocr.p12.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {


    @Id
    @GeneratedValue
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="raisonSociale")
    private String raisonSociale;

    @OneToMany(mappedBy="client", cascade= CascadeType.ALL)
    private Set<Evenement> evenements;



    public Client() {
    }

    public Client(int id, String nom, String prenom, String raisonSociale, Set<Evenement> evenements) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.raisonSociale = raisonSociale;
        this.evenements = evenements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }
}

package com.ocr.p12.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Evenement {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="titre")
    private String titre;

    @Column(name="adresse")
    private String adresse;

    @Column(name="dateDebut")
    private String dateDebut;

    @Column(name="dateFin")
    private String dateFin;

    @Column(name="duree")
    private double duree;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Client client;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Employe employe;

    public Evenement() {
    }


    public Evenement(int id, String titre, String adresse, String dateDebut, String dateFin, double duree, Client client, Employe employe) {
        this.id = id;
        this.titre = titre;
        this.adresse = adresse;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.client = client;
        this.employe = employe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }


}

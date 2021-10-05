package com.ocr.p12.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Employe {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="adresse")
    private String adresse;

    @Column(name="telephone")
    private String telephone;

    @Column(name="noCartePro")
    private String noCartePro;

    @Column(name="fonction")
    private String fonction;


    @OneToMany(mappedBy="employe")
    private Set<Evenement> evenements;


    public Employe() {
    }

    public Employe(int id, String nom, String prenom, String adresse, String telephone, String noCartePro, String fonction, Set<Evenement> evenements) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.noCartePro = noCartePro;
        this.fonction = fonction;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNoCartePro() {
        return noCartePro;
    }

    public void setNoCartePro(String noCartePro) {
        this.noCartePro = noCartePro;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }
}

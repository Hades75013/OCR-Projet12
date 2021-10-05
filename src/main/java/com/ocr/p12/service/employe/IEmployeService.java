package com.ocr.p12.service.employe;

import com.ocr.p12.model.Employe;

import java.util.List;
import java.util.Optional;

public interface IEmployeService {

    Employe sauvegarderEmploye(Employe employe);

    void supprimerEmploye(Employe employe);

    List<Employe> listeEmployes();

    Optional<Employe> recupererUnEmploye(int idEmploye);

    Double nombreHeuresTotalParEmploye(int idEmploye);
}

package com.ocr.p12.service.evenement;

import com.ocr.p12.model.Evenement;

import java.util.List;
import java.util.Optional;

public interface IEvenementService {

    Evenement sauvegarderEvenement(Evenement evenement);

    void supprimerEvenement(Evenement evenement);

    List<Evenement> listeEvenement();

    Optional<Evenement> recupererUnEvenement(int idEvenement);

    List<Evenement> listeEvenementsEmploye(Integer idEmploye);

    List<Evenement> listeEvenementsSansEmploye();

    Evenement attribuerEvenement(int idEmploye, int idEvenement);

    Evenement updateEvenement(String idEvenement, String dateDebut, String dateFin);
}

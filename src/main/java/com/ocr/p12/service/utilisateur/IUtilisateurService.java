package com.ocr.p12.service.utilisateur;

import com.ocr.p12.model.Utilisateur;

import java.util.Optional;

public interface IUtilisateurService {

    Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur);

    Optional<Utilisateur> recupererUnUtilisateur(int idUtilisateur);

    Utilisateur recupererUnUtilisateurParEmail(String email);


}

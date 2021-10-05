package com.ocr.p12.service.utilisateur;


import com.ocr.p12.dao.UtilisateurDAO;
import com.ocr.p12.model.RoleEnum;
import com.ocr.p12.model.Utilisateur;
import com.ocr.p12.web.security.BCryptManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class IUtilisateurServiceImpl implements IUtilisateurService {


    @Autowired
    UtilisateurDAO utilisateurDAO;


    @Override
    public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {

        Collection<RoleEnum> role = new ArrayList<>();
        role.add(RoleEnum.ADMIN);
        utilisateur.setRoles(role);

        utilisateur.setMotDePasse(BCryptManager.passwordEncoder().encode(utilisateur.getMotDePasse()));

        return utilisateurDAO.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> recupererUnUtilisateur(int idUtilisateur) {
        return utilisateurDAO.findById(idUtilisateur);
    }

    @Override
    public Utilisateur recupererUnUtilisateurParEmail(String email) {
        return utilisateurDAO.findByEmail(email);
    }
}

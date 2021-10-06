package com.ocr.p12.web.security;


import com.ocr.p12.model.Utilisateur;
import com.ocr.p12.service.utilisateur.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurSecurityService implements UserDetailsService {

    @Autowired
    private IUtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.recupererUnUtilisateurParEmail(email);
        if (utilisateur==null){
            throw new UsernameNotFoundException("l'utilisateur possèdant l'email "+email+" est introuvable");
        }else {
            return utilisateur;
        }
    }
}

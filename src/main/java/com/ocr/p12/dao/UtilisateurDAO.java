package com.ocr.p12.dao;


import com.ocr.p12.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur,Integer> {


    @Override
    <S extends Utilisateur> S save(S s);

    @Override
    Optional<Utilisateur> findById(Integer integer);

    Utilisateur findByEmail(String email);



}

package com.ocr.p12.dao;


import com.ocr.p12.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvenementDAO  extends JpaRepository<Evenement,Integer> {

    @Override
    List<Evenement> findAll();

    @Override
    <S extends Evenement> S save(S s);

    @Override
    Optional<Evenement> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Evenement evenement);

    @Query("select e from Evenement e where e.employe.id = :idEmploye")
    List<Evenement> findEventsByIdEmploye(Integer idEmploye);

    @Query("select e from Evenement e where e.employe.id = null")
    List<Evenement> findEvenementsSansEmploye();
}

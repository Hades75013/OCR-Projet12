package com.ocr.p12.dao;

import com.ocr.p12.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeDAO extends JpaRepository<Employe,Integer> {

    @Override
    List<Employe> findAll();

    @Override
    <S extends Employe> S save(S s);

    @Override
    Optional<Employe> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Employe employe);

    @Query("select SUM(duree) from Evenement e where e.employe.id = :idEmploye")
    Double findTotalHourByEmployeId(int idEmploye);
}

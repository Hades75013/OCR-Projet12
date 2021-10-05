package com.ocr.p12.dao;


import com.ocr.p12.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientDAO  extends JpaRepository<Client,Integer> {

    @Override
    List<Client> findAll();

    @Override
    <S extends Client> S save(S s);

    @Override
    Optional<Client> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Client client);

}

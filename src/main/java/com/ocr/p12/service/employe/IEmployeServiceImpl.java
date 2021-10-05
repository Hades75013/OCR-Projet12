package com.ocr.p12.service.employe;


import com.ocr.p12.dao.EmployeDAO;
import com.ocr.p12.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IEmployeServiceImpl implements IEmployeService {

    @Autowired
    EmployeDAO employeDAO;

    @Override
    public Employe sauvegarderEmploye(Employe employe) {
        return employeDAO.save(employe);
    }

    @Override
    public void supprimerEmploye(Employe employe) {
        employeDAO.delete(employe);
    }

    @Override
    public List<Employe> listeEmployes() {
        return employeDAO.findAll();
    }

    @Override
    public Optional<Employe> recupererUnEmploye(int idEmploye) {
        return employeDAO.findById(idEmploye);
    }

    @Override
    public Double nombreHeuresTotalParEmploye(int idEmploye) {
        return employeDAO.findTotalHourByEmployeId(idEmploye);
    }
}

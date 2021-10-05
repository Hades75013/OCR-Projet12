package com.ocr.p12.service.evenement;

import com.ocr.p12.dao.EmployeDAO;
import com.ocr.p12.dao.EvenementDAO;
import com.ocr.p12.model.Client;
import com.ocr.p12.model.Employe;
import com.ocr.p12.model.Evenement;
import com.ocr.p12.service.client.IClientService;
import com.ocr.p12.service.employe.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class IEvenementServiceImpl implements IEvenementService{

    @Autowired
    EvenementDAO evenementDAO;

    @Autowired
    EmployeDAO employeDAO;



    @Override
    public Evenement sauvegarderEvenement(Evenement evenement) {

        return evenementDAO.save(evenement);
    }

    @Override
    public void supprimerEvenement(Evenement evenement) {
        evenementDAO.delete(evenement);
    }

    @Override
    public List<Evenement> listeEvenement() {
        return evenementDAO.findAll();
    }

    @Override
    public Optional<Evenement> recupererUnEvenement(int idEvenement) {
        return evenementDAO.findById(idEvenement);
    }

    @Override
    public List<Evenement> listeEvenementsEmploye(Integer idEmploye) {
        return evenementDAO.findEventsByIdEmploye(idEmploye);
    }

    @Override
    public List<Evenement> listeEvenementsSansEmploye() {
        return evenementDAO.findEvenementsSansEmploye();
    }

    @Override
    public Evenement attribuerEvenement(int idEmploye, int idEvenement) {

        Optional<Employe> optionalEmploye=employeDAO.findById(idEmploye);
        Employe employe = optionalEmploye.get();

        Optional<Evenement> optionalEvenement=evenementDAO.findById(idEvenement);
        Evenement evenement = optionalEvenement.get();

        evenement.setEmploye(employe);

        evenementDAO.save(evenement);

        return evenement;
    }

    @Override
    public Evenement updateEvenement(String idEvenement,String dateDebut, String dateFin) {

        Optional<Evenement> optionalEvenement = evenementDAO.findById(Integer.valueOf(idEvenement));
        Evenement evenement = optionalEvenement.get();

        evenement.setDateDebut(dateDebut);
        evenement.setDateFin(dateFin);

        double nouvelleDuree = (double) ChronoUnit.MINUTES.between(LocalDateTime.parse(dateDebut),LocalDateTime.parse(dateFin))/60;

        evenement.setDuree(nouvelleDuree);


        evenementDAO.save(evenement);


        return evenement;
    }
}

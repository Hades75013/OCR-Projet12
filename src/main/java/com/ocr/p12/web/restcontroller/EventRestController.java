package com.ocr.p12.web.restcontroller;


import com.ocr.p12.model.Evenement;
import com.ocr.p12.service.evenement.IEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventRestController {

    @Autowired
    IEvenementService evenementService;


    @PostMapping(value="attribuerEvenement")
    public Evenement attribuerEvenement(@RequestParam(value = "idEmploye")int idEmploye, Integer idEvenement){

        Evenement evenement = evenementService.attribuerEvenement(idEmploye, idEvenement);

        return evenement;
    }


    @GetMapping(value="listeEvenementsEmploye")
    public List<Evenement> listeEvenementsParEmploye(@RequestParam(value = "idEmploye") Integer idEmploye){

        List<Evenement> listeEvenementsEmploye = evenementService.listeEvenementsEmploye(idEmploye);

        return listeEvenementsEmploye;
    }


    @PostMapping(value="updateEvenement")
    public Evenement miseAJourEvenement(String idEvenement,String dateDebut, String dateFin) {

        Evenement evenement = evenementService.updateEvenement(idEvenement, dateDebut, dateFin);

        return evenement;
    }


}

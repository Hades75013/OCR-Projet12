package com.ocr.p12.web.controller;


import com.ocr.p12.model.Client;
import com.ocr.p12.model.Employe;
import com.ocr.p12.model.Evenement;
import com.ocr.p12.model.Utilisateur;
import com.ocr.p12.service.client.IClientService;
import com.ocr.p12.service.employe.IEmployeService;
import com.ocr.p12.service.evenement.IEvenementService;
import com.ocr.p12.service.utilisateur.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    IUtilisateurService utilisateurService;

    @Autowired
    IClientService clientService;

    @Autowired
    IEmployeService employeService;

    @Autowired
    IEvenementService evenementService;



    //CONTROLLER UTILISATEUR

    @GetMapping(value="/accueil")
    public String Accueil (){

        return "accueil";
    }

    @GetMapping(value="/ajouterUtilisateur")
    public String Inscription (Model model){

        model.addAttribute("utilisateur",new Utilisateur());

        return "forminscription";
    }

    @PostMapping(value="/sauvegarderUser")
    public String SaveUtilisateur(Utilisateur utilisateur, Model model){

        utilisateurService.sauvegarderUtilisateur(utilisateur);

        model.addAttribute("utilisateur", utilisateur);

        return "confirmationinscription";
    }

    @GetMapping(value="/connection")
    public String Connection (){

        return "formconnection";
    }

    @PostMapping(value="/login")
    public ModelAndView ConnecterUtilisateur (){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/espaceperso");
        }
        return new ModelAndView("formconnection");
    }

    @GetMapping(value="/deconnection")
    public String DeconnecterUtilisateur (){

        SecurityContextHolder.clearContext();

        return "redirect:/Connection";
    }

    @GetMapping(value="/espacePerso")
    public String EspacePerso (Model model){

        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Employe> employes = employeService.listeEmployes();

        model.addAttribute("listeEmployes", employes);
        model.addAttribute("utilisateur", utilisateur);

        return "espaceperso";
    }

    @GetMapping(value="/consulterRessources")
    public String ConsulterRessources (Model model){

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        return "listeressources";
    }





    //CONTROLLER EMPLOYE

    @GetMapping(value="/ajouterEmploye")
    public String ajouterEmploye(Model model) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        model.addAttribute("employe",new Employe());

        return "formajoutemploye";

    }

    @PostMapping(value="saveEmploye")
    public String sauverEmploye(Model model, @Valid Employe employe, BindingResult bindingResult) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        if(bindingResult.hasErrors()) {

            model.addAttribute("employe",employe);

            return "formajoutemploye";
        }

        model.addAttribute("employe",employe);

        employeService.sauvegarderEmploye(employe);

        return "confirmationajoutemploye";
    }

    @GetMapping(value="employeDetails")
    public String EmployesDetails (int idEmploye, Model model){

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Employe> optionalEmploye=employeService.recupererUnEmploye(idEmploye);
        Employe employe = optionalEmploye.get();


        model.addAttribute("employe",employe);

        return "employedetails";
    }

    @GetMapping(value="modifierEmploye")
    public String modifEmploye(Model model, int idEmploye) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Employe> optionalEmploye=employeService.recupererUnEmploye(idEmploye);
        Employe employe = optionalEmploye.get();

        model.addAttribute("employe",employe);

        return "formmodifemploye";
    }

    @PostMapping(value="saveModifEmploye")
    public String sauverModifEmploye(Model model, @Valid Employe employe, BindingResult bindingResult) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        if(bindingResult.hasErrors()) {

            model.addAttribute("employe",employe);

            return "formmodifemploye";
        }

        model.addAttribute("employe",employe);

        employeService.sauvegarderEmploye(employe);

        return "redirect:/listeEmployes";
    }

    @GetMapping(value="supprimerEmploye")
    public String supprimerEmploye (Model model, int idEmploye) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Employe> optionalEmploye=employeService.recupererUnEmploye(idEmploye);
        Employe employe = optionalEmploye.get();

        employeService.supprimerEmploye(employe);

        return "redirect:/listeEmployes";
    }

    @GetMapping(value="listeEmployes")
    public String Employes (Model model){

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        List<Employe> employes = employeService.listeEmployes();

        model.addAttribute("listeEmployes", employes);

        return "listeemployes";
    }

    @GetMapping(value="planningEmploye")
    public String PlanningParEmploye (Model model, @RequestParam(value = "idEmploye") int idEmploye){

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Employe> optionalEmploye = employeService.recupererUnEmploye(idEmploye);
        Employe employe = optionalEmploye.get();

        List<Evenement> listeEvenementsNonAttribues = evenementService.listeEvenementsSansEmploye();

        model.addAttribute("employe",employe);
        model.addAttribute("listeEvenementsNonAttribues",listeEvenementsNonAttribues);

        return "fullcalendar";
    }







    //CONTROLLER CLIENT

    @GetMapping(value="/ajouterClient")
    public String ajouterClient(Model model) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        model.addAttribute("client",new Client());

        return "formajoutclient";

    }

    @PostMapping(value="saveClient")
    public String sauverClient(Model model, @Valid Client client, BindingResult bindingResult) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        if(bindingResult.hasErrors()) {

            model.addAttribute("client",client);

            return "formajoutclient";
        }

        model.addAttribute("client",client);

        clientService.sauvegarderClient(client);

        return "confirmationajoutclient";
    }

    @GetMapping(value="modifierClient")
    public String modifClient(Model model, int idClient) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Client> optionalClient=clientService.recupererUnClient(idClient);
        Client client = optionalClient.get();

        model.addAttribute("client",client);

        return "formmodifclient";
    }

    @PostMapping(value="saveModifClient")
    public String sauverModifClient(Model model, @Valid Client client, BindingResult bindingResult) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        if(bindingResult.hasErrors()) {

            model.addAttribute("client",client);

            return "formmodifclient";
        }

        model.addAttribute("client",client);

        clientService.sauvegarderClient(client);

        return "redirect:/listeClients";
    }

    @GetMapping(value="supprimerClient")
    public String supprimerClient (Model model, int idClient) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Client> optionalClient=clientService.recupererUnClient(idClient);
        Client client = optionalClient.get();

        clientService.supprimerClient(client);

        return "redirect:/listeClients";
    }

    @GetMapping(value="listeClients")
    public String Clients (Model model){

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        List<Client> clients =clientService.listeClients();

        model.addAttribute("listeClients", clients);

        return "listeclients";
    }







    //CONTROLLER EVENEMENT

    @GetMapping(value="ajouterEvenement")
    public String ajouterEvenement(Model model,@RequestParam(value = "idClient")int idClient) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        model.addAttribute("evenement",new Evenement());
        model.addAttribute("idClient",idClient);

        return "formajoutevenement";

    }

    @PostMapping(value="saveEvenement")
    public String sauverEvenement(Model model, @Valid Evenement evenement, @RequestParam(value = "idClient")Integer idClient, BindingResult bindingResult) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        if(bindingResult.hasErrors()) {

            model.addAttribute("evenement",evenement);

            return "formajoutevenement";
        }

        model.addAttribute("evenement",evenement);

        Optional<Client> optionalClient=clientService.recupererUnClient(idClient);
        Client client = optionalClient.get();
        evenement.setClient(client);

        evenementService.sauvegarderEvenement(evenement);

        return "confirmationajoutevenement";
    }

    @GetMapping(value="modifierEvenement")
    public String modifEvenement(Model model, int idEvenement) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Evenement> optionalEvenement=evenementService.recupererUnEvenement(idEvenement);
        Evenement evenement = optionalEvenement.get();

        model.addAttribute("evenement",evenement);

        return "formmodifevenement";
    }

    @PostMapping(value="saveModifEvenement")
    public String sauverModifEvenement(Model model, @Valid Evenement evenement, BindingResult bindingResult) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        if(bindingResult.hasErrors()) {

            model.addAttribute("evenement",evenement);

            return "formmodifevenement";
        }

        model.addAttribute("evenement",evenement);

        evenementService.sauvegarderEvenement(evenement);

        return "redirect:/listeEvenements";
    }

    @GetMapping(value="supprimerEvenement")
    public String supprimerEvenement (Model model, int idEvenement) {

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        Optional<Evenement> optionalEvenement=evenementService.recupererUnEvenement(idEvenement);
        Evenement evenement = optionalEvenement.get();

        evenementService.supprimerEvenement(evenement);

        return "redirect:/listeEvenements";
    }


    @GetMapping(value="listeEvenements")
    public String Evenements (Model model){

        Utilisateur utilisateur = (Utilisateur)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        List<Evenement> evenements = evenementService.listeEvenement();

        model.addAttribute("listeEvenements", evenements);

        return "listeevenements";
    }


}
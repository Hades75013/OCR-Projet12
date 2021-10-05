package com.ocr.p12.service.client;

import com.ocr.p12.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    Client sauvegarderClient(Client client);

    void supprimerClient(Client client);

    List<Client> listeClients();

    Optional<Client> recupererUnClient(int idClient);


    Optional<Client> recupererUnClientParNom(String nomClient);
}

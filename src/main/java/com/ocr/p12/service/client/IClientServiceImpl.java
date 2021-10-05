package com.ocr.p12.service.client;

import com.ocr.p12.dao.ClientDAO;
import com.ocr.p12.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IClientServiceImpl implements IClientService{

    @Autowired
    ClientDAO clientDAO;


    @Override
    public Client sauvegarderClient(Client client) {
        return clientDAO.save(client);
    }

    @Override
    public void supprimerClient(Client client) {
        clientDAO.delete(client);
    }

    @Override
    public List<Client> listeClients() {
        return clientDAO.findAll();
    }

    @Override
    public Optional<Client> recupererUnClient(int idClient) {
        return clientDAO.findById(idClient);
    }

    @Override
    public Optional<Client> recupererUnClientParNom(String nomClient) {
        return clientDAO.findByNom(nomClient);
    }
}

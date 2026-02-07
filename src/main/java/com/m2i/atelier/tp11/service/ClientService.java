package com.m2i.atelier.tp11.service;


import com.m2i.atelier.tp11.exceptions.CompteIntrouvableException;
import com.m2i.atelier.tp11.model.Client;
import com.m2i.atelier.tp11.model.CompteBancaire;

import java.util.List;
import java.util.Optional;

public class ClientService {


    public Optional <Client> rechercherClientParId(List<Client> clients, int id){

        return clients.stream().filter(client -> client.getId()==id).findFirst();

    }



    public Optional <CompteBancaire> rechercherCompteParNumero(List<CompteBancaire> comptes, long numero){

        return comptes.stream().filter(compte -> compte.getId()==numero).findFirst();

    }



}

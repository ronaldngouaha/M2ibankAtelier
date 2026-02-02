package com.m2i.atelier.tp7.utils;

import com.m2i.atelier.tp7.model.Client;
import com.m2i.atelier.tp7.model.CompteBancaire;
import com.m2i.atelier.tp7.model.HistoriqueOperation;

import java.util.List;

public  class RapportActivite {


    public RapportActivite(){}

    public static void generer(List<Client> clients, List<CompteBancaire> comptes, HistoriqueOperation historique){

        System.out.println("===== RAPPORT D'ACTIVITÉ =====");

        // Nombre total de clients
        System.out.println(">>>>>>Nombre total de clients : " + clients.size());

        //Nombre total de comptes

        System.out.println(">>>>>>Nombre total de comptes "+ comptes.size());

        // Solde global de la banque

        double soldeGlobal = comptes.stream()              // transforme la liste en stream
                .mapToDouble(CompteBancaire::getSolde) // extrait le solde de chaque compte
                .sum();

        System.out.println(">>>>>>Solde global de la banque "+ soldeGlobal+" $");

        //Nombre total de transactions

        System.out.println(">>>>>>Nombre total de transactions "+ historique.getTransactions().size());

        System.out.println("=============================");
    }
}

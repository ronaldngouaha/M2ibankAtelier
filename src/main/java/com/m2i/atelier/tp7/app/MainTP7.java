package com.m2i.atelier.tp7.app;

import com.m2i.atelier.tp7.model.ClientPremium;
import com.m2i.atelier.tp7.model.CompteBancaire;
import com.m2i.atelier.tp7.model.CompteCourant;
import com.m2i.atelier.tp7.model.CompteEpargne;
import com.m2i.atelier.tp7.service.OperationBancaire;
import com.m2i.atelier.tp7.utils.M2iBankUtils;

import java.util.ArrayList;
import java.util.List;


public class MainTP7 {

    public static void main(String[] args){


        //
        List< CompteBancaire> comptes= new ArrayList<>();

        //Ici je cree quelques clients
        ClientPremium clientPremium1= new ClientPremium("John Doe", "jony","johy@gmail.com",10,3000);
        ClientPremium clientPremium2= new ClientPremium("Alex Doe", "alexy","alexy@gmail.com",5,2000);

        //ici on cree des comptes pour les deux client crees plus haut
        comptes.add(new CompteEpargne(clientPremium1, 2900, 0.23));
        comptes.add(new CompteCourant(clientPremium1, 1200, 3000));


        comptes.add(new CompteEpargne(clientPremium2, 3000, 0.23));
        comptes.add(new CompteCourant(clientPremium2, 790, 4000));

        for(CompteBancaire compte: comptes){

            //ici on affiche les donnees de chaque compte
            compte.afficherInfos();
            compte.appliquerOperation();

            System.out.println("Après opération :");
            compte.afficherInfos();
            System.out.println("--------------------");



        }


        //ICI NOUS CHARGEONS LE Menu interactif
        M2iBankUtils.afficherMenu();

    }
}

package com.m2i.atelier.tp10.app;

import com.m2i.atelier.tp10.model.*;
import com.m2i.atelier.tp10.utils.FichierUtils;
import com.m2i.atelier.tp10.utils.M2iBankUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainTP10 {

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
        HistoriqueOperation historiqueOperation= new HistoriqueOperation();

        for(CompteBancaire compte: comptes){

            Transaction transaction = new Transaction(compte,203, TypeOperation.DEPOT, LocalDate.now());

            historiqueOperation.ajouterTransaction(transaction);

            //ici on affiche les donnees de chaque compte
            compte.afficherInfos();
            compte.appliquerOperation();

            System.out.println("Après opération :");
            compte.afficherInfos();
            System.out.println("--------------------");

        }

        System.out.println("test->>>>>>>");
        historiqueOperation.filtrerParMontant(203,206).forEach(Transaction::afficherDetails);

        System.out.println("test->>>>>>>");
        historiqueOperation.filtrerParType(TypeOperation.DEPOT).forEach(Transaction::afficherDetails);

        String chemin="public/media/operations.txt";
        //System.out.println("Répertoire courant : " + getRepertoireCourant());
        FichierUtils.lireOperations(chemin);

        List<String> rapport= new ArrayList<>();

        rapport.add("Nombre total de comptes "+comptes.size());
        rapport.add("Solde global "+comptes.stream().mapToDouble(CompteBancaire::getSolde).sum()+" $");
        rapport.add("Nombre de transactions "+historiqueOperation.getTransactions().stream().mapToDouble(Transaction::getMontant).sum()+" $");

        /// Générer un rapport d’activité bancaire
        FichierUtils.genererRapport(chemin,rapport);


        //Gérez les exceptions avec catch et affichez un message d’erreur. Tester en cherchant à lire les fichiers d’un fichier qui n’existe pas (test.txt)
        FichierUtils.lireOperations(chemin);

        //Utilisez Files.lines() et filter() pour afficher uniquement les lignes contenant le type (ex : "debit")
        FichierUtils.afficherOperationsParType(chemin,"debit");


        //ICI NOUS CHARGEONS LE Menu interactif
       // M2iBankUtils.afficherMenu();



    }
//Voici LA fonction standard pour obtenir le chemin du répertoire courant (le working directory).
    public static String getRepertoireCourant() {
        return System.getProperty("user.dir");
    }

}

package com.m2i.atelier.tp12.app;

import com.m2i.atelier.tp12.exceptions.CompteIntrouvableException;
import com.m2i.atelier.tp12.model.*;
import com.m2i.atelier.tp12.service.ClientService;
import com.m2i.atelier.tp12.utils.FichierUtils;
import com.m2i.atelier.tp12.utils.ProcessUtils;



import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class M2IBankApp {

    //private static final Logger logger = LoggerFactory.getLogger(FichierUtils.class);

    public static void main(String[] args){

      /*  logger.info("Application démarrée");
        logger.warn("Attention : seuil presque atteint");
        logger.error("Erreur critique !");*/

        ProcessUtils.lancerScriptSauvegarde("ping", "google.com");

        long recentAddAcc = 0;

        List<CompteBancaire> comptes= new ArrayList<>();

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
            recentAddAcc=compte.getId();
            Transaction transaction = new Transaction(compte,203, TypeOperation.DEPOT);

            historiqueOperation.ajouterTransaction(transaction);

            //ici on affiche les donnees de chaque compte
            compte.afficherInfos();
            compte.appliquerOperation();

            System.out.println("Après opération :");
            compte.afficherInfos();
            System.out.println("--------------------");

        }

        ClientService clientService= new ClientService();

        final long acc=recentAddAcc;
        clientService.rechercherCompteParNumero(comptes, new Random().nextLong());

        // Utilisez orElseThrow pour lever une exception personnalisée si le compte est introuvable.
        clientService.rechercherCompteParNumero( comptes, recentAddAcc).orElseThrow( ()->
                new CompteIntrouvableException(
                        "❌ Compte introuvable avec le numéro " + acc
                ));


        //Generation du fichier pour un compte client

       // FichierUtils.genererReleveAvecHorodatage(comptes.get(1));

        FichierUtils.exporterTransactionsXLSX("clients.xlsx",historiqueOperation.getTransactions());

       //Ici on cree un objet transaction
        Transaction transaction = new Transaction(comptes.get(1),900.99, TypeOperation.DEPOT);

        //ici on recupere la date
        Instant horodatage = transaction.getHorodatage();

// Heure locale, ici on va recuperer la zone id du system et afficher l'heure local
        ZonedDateTime heureLocale =
                ZonedDateTime.ofInstant(horodatage, ZoneId.systemDefault());

// Affichage
        System.out.println("⏰ Heure locale : " + heureLocale);
        System.out.println("🌍 Heure UTC    : " + horodatage.toString());

        String cheminExcel="public/media/clients.xlsx";
        try {
            FichierUtils.lireClients2(cheminExcel);
        } catch (Exception e) {
            System.err.println("Impossible de lire le fichier Excel : " + e.getMessage());
        }
    }


}

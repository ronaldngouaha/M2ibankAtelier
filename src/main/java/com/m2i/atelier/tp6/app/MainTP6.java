package com.m2i.atelier.tp6.app;

import com.m2i.atelier.tp6.model.*;

import java.util.ArrayList;
import java.util.List;

public class MainTP6 {


      static void main (String[] args){


        List<CompteBancaire> comptes = new ArrayList<>();

        // Ajout de comptes différents
        comptes.add(new CompteCourant("CC001", 1500, 500));
        comptes.add(new CompteEpargne("CE001", 3000, 0.03));
        comptes.add(new CompteCourant("CC002", 800, 300));
        comptes.add(new CompteEpargne("CE002", 5000, 0.04));


        // Parcours et appel polymorphe
        for (CompteBancaire compte : comptes) {
            compte.afficherInfos();   // comportement dépend du type réel

            //calcul et affichage de l'intérêt
            if(compte instanceof CompteEpargne){
                ((CompteEpargne) compte).afficherInteret();
            }
            System.out.println("----------------------");

            RetraitService retraitService= new RetraitService(compte);
            //Le dépôt et le retrait sont exécutés sans changer le code
            executerService(retraitService, 2000);
            DepotService depotService = new DepotService(compte);

            //Le dépôt et le retrait sont exécutés sans changer le code
            executerService(depotService,300);

            System.out.println("----------------------");
        }

    }


    //Le paramètre est de type AbstractTransactionService
    //On peut lui passer n’importe quelle sous-classe :
    //DepotService
    //RetraitService
    public static void executerService(AbstractTransactionService service, double montant){

        service.executerTransaction(montant);
        service.afficheSolde();


    }
}

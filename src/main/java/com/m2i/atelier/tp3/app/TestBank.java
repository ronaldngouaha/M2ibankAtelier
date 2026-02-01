package com.m2i.atelier.tp3.app;


import com.m2i.atelier.tp3.model.CompteBancaire;
import com.m2i.atelier.tp3.model.CustomizeException;
import com.m2i.atelier.tp3.model.TransactionServiceImpl;

public class TestBank {


    /*
    Dans une classe Main (package com.m2i.app), créez des objets CompteBancaire, Transaction
    Appelez les méthodes deposer, retirer, afficherInfos, afficherDetails
    Testez les cas limites (ex : retrait supérieur au solde, type invalide)

    * */
    public static void main( String[] args){

        CompteBancaire compteBancaire= new CompteBancaire("John Doe", 0);

        TransactionServiceImpl transactionService= new TransactionServiceImpl();

             // ici on effectue un credit sur le compte du client
            transactionService.doTransaction(compteBancaire,200, "CREDIT");

            //  Ici om effectue un  debit sur le compte du client
            transactionService.doTransaction(compteBancaire,90, "DEBIT");


            // ici on effectue un debit avec le parametre transaction type erroné

            transactionService.doTransaction(compteBancaire,90, "DEBAT");

            //  Ici om effectue un  debit sur le compte du client en levant une exception
            transactionService.doTransaction(compteBancaire,300, "DEBIT");



    }
}

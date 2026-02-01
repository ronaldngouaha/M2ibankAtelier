package com.m2i.atelier.tp5.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;



public class TransactionServiceImpl implements TransactionService {




    @Override
    public void doTransaction(CompteBancaire compteBancaire, double montant, String transactionType) {


        double balance=0, oldBalance=compteBancaire.getSolde();
        // Initialisation de l'objet transaction
        Transaction transaction= new Transaction(compteBancaire,montant,transactionType);


        switch (transactionType){

            case "DEBIT":

                    // On va lever une exception lorsque le montant de la transaction sera supperieur au compte en banque
                    if(compteBancaire.getSolde()<montant){
                        try {
                            throw new CustomizeException.SoldeInsuffisantException("----> SOLDE INSUFFISANT POUR EFFECTUER CETTE TRANSACTION.");
                        } catch (CustomizeException.SoldeInsuffisantException e) {
                            System.err.println("***Exception CATCH*** ->>: "+ e.getMessage());

                            // On va ajouter un suivi sur les mouvements bancaire du compte
                            this.accountTracker(compteBancaire, transaction, oldBalance);
                        }


                    }else{


                        balance=compteBancaire.getSolde()-montant;

                        compteBancaire.setSolde(balance);

                        afficherDetails(transaction);

                        // On va ajouter un suivi sur les mouvements bancaire du compte
                        this.accountTracker(compteBancaire, transaction, oldBalance);

                    }

                    break;
            case "CREDIT":

                balance=compteBancaire.getSolde()+montant;
                compteBancaire.setSolde(balance);

                // On va ajouter un suivi sur les mouvements bancaire du compte
                afficherDetails(transaction);
                this.accountTracker(compteBancaire, transaction, oldBalance);

                break;

         case "TRANSFER" :
                    //TODO Implementation de la classe transaction apres

            default :
                    System.out.println("***ERROR** ----> TRANSACTION TYPE NOT FOUND");
            };

    }





    // Cette fonction permet de afficher les details d'une transaction

    @Override
    public void  afficherDetails(Transaction transaction){
        Optional<String> titulaire = Optional.ofNullable(transaction.getCompte().getTitulaire());
        System.out.println(String.format("***TRX*** -->>ID: %d;  Titulaire %s: Montant: %.2f $, Type: %s; Id: %d; Date: %s", transaction.getId(), titulaire.get(), transaction.getMontant(), transaction.getType(), transaction.getId(), transaction.getDate()));

    }


    // Cette fonction permet de tracer toutes les operations bancaires en base de donnees
    @Override
    public void accountTracker(CompteBancaire compteBancaire, Transaction transaction, double oldBalance){

        System.out.println("**********************************************************************************************************");
        System.out.println("");
        System.out.println(String.format("***MVT**** <<-- Titulaire %s: Solde Avant: %.2f $, Montant: %.2f$ ; New Balance: %.2f$ ; Remark: %s; Date: %s --->", compteBancaire.getTitulaire(), oldBalance, transaction.getMontant(), compteBancaire.getSolde(), transaction.getType(), transaction.getDate()));
       System.out.println("");
        System.out.println("**********************************************************************************************************");
    }
}

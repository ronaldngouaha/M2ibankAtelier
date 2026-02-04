package com.m2i.atelier.tp6.model;

public class RetraitService extends AbstractTransactionService{

    public RetraitService(CompteBancaire compteBancaire){
        super(compteBancaire);
    }

    @Override
    public void executerTransaction(double montant) {

        if (montant > 0 && compte.getSolde() >= montant) {
            compte.setSolde(compte.getSolde() - montant);
            System.out.println("Retrait effectué : -" + montant);
        } else {
            System.out.println("Retrait impossible (montant invalide ou solde insuffisant).");
            System.out.println("Vous serez a découvert");

            compte.setSolde(0);
            double montantDecouvert=montant-compte.getSolde();

            if(compte instanceof CompteCourant){

                ((CompteCourant) compte).setDecouvert(((CompteCourant) compte).getDecouvert() + montantDecouvert);

            }

        }
        this.afficheSolde();
        //compte.afficherInfos();
    }
}
